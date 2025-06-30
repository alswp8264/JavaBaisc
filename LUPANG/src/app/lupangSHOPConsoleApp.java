package app;

import static app.InputUtil.safeReadInt;
import static app.InputUtil.safeReadString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import Cart.CartDAO;
import Cart.CartService;
import Cart.CartServiceImpl;
import Cart.HashMapCartDAO;
import admin.Transaction;
import admin.TransactionService;
import item.ItemDAO;
import item.ItemVO;
import item.file.ObjFileHashMapItemDAO;
import member.MJMemberService;
import member.Member;
import member.MemberService;
import member.ObjFileHashMapMemberDAO;

public class lupangSHOPConsoleApp {

    private Scanner sc = new Scanner(System.in);
    private ItemDAO itemDAO = new ObjFileHashMapItemDAO();
    private MemberService memberService = new MJMemberService(new ObjFileHashMapMemberDAO());
    private CartService cartService;
    private Member loggedMember; // ✅ Member로 명확히
    
    

    	public void run() {
    	    // DAO 및 서비스 구성
    	    CartDAO cartDAO = new HashMapCartDAO();
    	    this.cartService = new CartServiceImpl(cartDAO); // ⚠️ this 사용, 이중 선언 방지

    	    // loggedMember는 반드시 로그인 이후에 값이 들어가 있어야 함
    	    ConsumerMenu cm = new ConsumerMenu(loggedMember, itemDAO, cartService, sc, memberService);
    	    
    	
        ensureAdminAccount(); // ✅ 관리자 계정 사전 등록
        while (true) {
            System.out.println("\n==== 루팡샵 메인 화면 ====");
            System.out.println("1. 아이템 목록 보기");
            System.out.println("2. 로그인");
            System.out.println("3. 회원 가입");
            System.out.println("0. 종료");
            int sel = safeReadInt(">> 선택: ");

            switch (sel) {
                case 0 -> {
                    System.out.println("종료합니다.");
                    return;
                }
                case 1 -> listItems();
                case 2 -> login();
                case 3 -> signUp();
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }

    /** 관리자 계정 자동 등록 */
    private void ensureAdminAccount() {
        Member admin = memberService.login("KO유라", "1234");
        if (admin == null) {
            Member newAdmin = new Member("KO유라", "관리자", 0, 0, "1234", "관리자");
            boolean result = memberService.registMember(newAdmin);
            if (result) {
            }
        }
    }

    /** 로그인 분기 (관리자/일반 사용자) */
    private void login() {
        String id = safeReadString("ID: ");
        String pw = safeReadString("PW: ");

        Member member = memberService.login(id, pw);
        if (member == null) {
            System.out.println("❌ 로그인 실패");
            return;
        }

        this.loggedMember = member;

        if ("관리자".equals(member.getRole())) {
            System.out.println("✅ 관리자 로그인 성공");
            controlAdminMenu();
        } else {
            System.out.println("✅ 일반 사용자 로그인 성공");
            new ConsumerMenu(member, itemDAO, cartService, sc, memberService).run();
        }
    }

    /** 회원 가입 처리 */
    private void signUp() {
        System.out.println("\n[회원 가입]");
        String id = safeReadString("ID 입력: ");

        // ✅ 관리자 ID 가입 차단
        if ("admin".equalsIgnoreCase(id) || "KO유라".equalsIgnoreCase(id)) {
            System.out.println("❌ 해당 ID는 사용할 수 없습니다. (관리자 예약 ID)");
            return;
        }

        String pw = safeReadString("비밀번호 입력: ");
        String nickname = safeReadString("닉네임 입력: ");

        Member newMember = new Member(id, nickname, 0, 0, pw, "일반"); // ✅ role 고정
        boolean result = memberService.registMember(newMember);

        if (result) {
            System.out.println("✅ 회원가입 성공! 로그인 해주세요.");
        } else {
            System.out.println("❌ 이미 존재하는 ID입니다.");
        }
    }

    /** 아이템 목록 조회 */
    private void listItems() {
        String inputGame = safeReadString("게임명 (정확히 입력, 예: 메이플스토리): ").trim();
        boolean found = false;

        System.out.printf("%-4s| %-20s| %-10s| %-10s| %-12s| %-10s| %-10s| %-8s%n",
                "ID", "아이템명", "게임", "서버", "가격", "판매자", "상태", "비고"); 
        
        for (ItemVO i : itemDAO.selectAll()) {
            // null 방지 및 정규화
            String itemGame = (i.getGame() != null) ? i.getGame().trim() : "";
            String itemStatus = (i.getStatus() != null) ? i.getStatus().trim() : "";


            // 상태 체크 + 게임명 정확 비교
            if(itemGame.equals(inputGame) &&
            		(itemStatus.equals(ItemVO.STATUS_LISTED) || itemStatus.equals(ItemVO.STATUS_RESERVED))){
            //if (itemGame.equals(inputGame) && (itemStatus.equals("판매 중") || itemStatus.equals("예약 중"))) {

                String sellerName = (i.getSeller() != null) ? i.getSeller().getNickname() : "미상";
                String server = (i.getServer() != null) ? i.getServer() : "-";
                String remark = (loggedMember != null && i.getSeller() != null && i.getSeller().equals(loggedMember)) ? "내 아이템" : "";

                System.out.printf("%-4d| %-20s| %-10s| %-10s| %,10d원| %-10s| %-10s| %-8s%n",
                        i.getItemId(), i.getName(), itemGame, server, i.getPrice(), sellerName, itemStatus, remark);
                found = true;
            }
        }

        if (!found) {
            System.out.println("❌ 해당 게임의 '판매 중' 또는 '예약 중' 상태 아이템이 없습니다.");
        }
    }




    /** 관리자 메뉴 (생략하지 않고 유지) */
    private void controlAdminMenu() {
        TransactionService txService = new TransactionService();

        while (true) {
            System.out.println("\n==== 관리자 메뉴 ====");
            System.out.println("1. 전체 회원 목록 조회");
            System.out.println("2. 전체 아이템 목록 조회");
            System.out.println("3. 전체 거래 목록 조회");
            System.out.println("4. 거래 상태 수동 변경");
            System.out.println("5. 회원 강제 탈퇴");
            System.out.println("6. 아이템 삭제");
            System.out.println("7. 게임별 아이템 수량 확인");
            System.out.println("0. 로그아웃");
            System.out.print(">> 선택: ");

            String input = sc.nextLine().trim();
            switch (input) {
                case "1" -> viewAllMembers();
                case "2" -> viewAllItems();
                case "3" -> viewAllTransactions(txService);
                case "4" -> changeTransactionStatus(txService);
                case "5" -> forceRemoveMember();
                case "6" -> deleteItemById();
                case "7" -> printItemSummaryByGame();
                case "0" -> {
                    System.out.println("로그아웃합니다.");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다.");
            }
        }
    }

    private void viewAllMembers() {
        List<Member> members = memberService.listMembers();
        Member.printTable(members);
    }

 // ✅ 관리자 메뉴 - 아이템 전체 조회 (한글 상태 적용)
    private void viewAllItems() {
        List<ItemVO> items = itemDAO.selectAll();
        if (items.isEmpty()) {
            System.out.println("등록된 아이템이 없습니다.");
            return;
        }

        Map<String, List<ItemVO>> grouped = new TreeMap<>();
        for (ItemVO item : items) {
            String sellerName = (item.getSeller() != null) ? item.getSeller().getNickname() : "미상";
            grouped.computeIfAbsent(sellerName, k -> new ArrayList<>()).add(item);
        }

        System.out.println("\n판매자별 아이템 목록");
        for (Map.Entry<String, List<ItemVO>> entry : grouped.entrySet()) {
            System.out.println("\n판매자: " + entry.getKey());
            for (ItemVO item : entry.getValue()) {
                String buyer = (item.getBuyer() != null) ? item.getBuyer().getNickname() : "-";
                String statusLabel = item.getStatusLabel();  // ✅ 수정된 부분
                String line = String.format(" - [%04d] %s - %,d원 / 상태: %s", item.getItemId(), item.getName(), item.getPrice(), statusLabel);
                if (!buyer.equals("-")) {
                    line += " / 구매자: " + buyer;
                }
                System.out.println(line);
            }
        }
    }

    // ✅ 관리자 메뉴 - 전체 거래 조회 (한글 상태 적용)
    private void viewAllTransactions(TransactionService txService) {
        System.out.println("\n전체 거래 목록:");
        List<Transaction> txList = txService.getAll();
        if (txList.isEmpty()) {
            System.out.println("거래 내역 없음");
        } else {
            for (Transaction tx : txList) {
                String itemName = tx.getItem() != null ? tx.getItem().getName() : "알 수 없음";
                String status = tx.getItem() != null ? tx.getItem().getStatusLabel() : "없음";  // ✅ 수정된 부분
                String buyer = tx.getBuyer() != null ? tx.getBuyer().getNickname() : "미정";

                System.out.printf("- 거래번호 %d | 아이템: %s | 상태: %s | 구매자: %s\n", tx.getId(), itemName, status, buyer);
            }
        }
    }


    private void changeTransactionStatus(TransactionService txService) {
        int txId = safeReadInt("거래 ID 입력: ");
        Transaction tx = txService.getTransaction(txId);
        if (tx == null) {
            System.out.println("해당 거래가 존재하지 않습니다.");
            return;
        }

        System.out.println("현재 상태: " + tx.getItem().getStatus());
        String newStatus = safeReadString("변경할 상태 입력 (예: 판매 중, 예약 중, 거래 완료, 판매 중지): ");
        List<String> validStatuses = Arrays.asList("판매 중", "예약 중", "거래 완료", "판매 중지");

        if (validStatuses.contains(newStatus)) {
            tx.getItem().setStatus(newStatus);
            itemDAO.update(tx.getItem());
            System.out.println("상태 변경 완료");
        } else {
            System.out.println("잘못된 상태입니다.");
        }
    }

    private void forceRemoveMember() {
        String id = safeReadString("강제 탈퇴할 회원 ID 입력: ");
        boolean removed = memberService.deleteById(id);
        if (removed) {
            System.out.println("회원이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID를 가진 회원이 존재하지 않습니다.");
        }
    }

    private void deleteItemById() {
        int id = safeReadInt("삭제할 아이템 ID 입력: ");
        ItemVO item = itemDAO.selectById(id);
        if (item != null) {
            itemDAO.delete(id);
            System.out.println("아이템이 삭제되었습니다.");
        } else {
            System.out.println("해당 ID의 아이템이 없습니다.");
        }
    }

    private void printItemSummaryByGame() {
        Map<String, Map<String, Integer>> gameItemMap = new TreeMap<>();

        for (ItemVO item : itemDAO.selectAll()) {
            String game = item.getGame();
            String name = item.getName();
            int qty = item.getQuantity();

            gameItemMap.computeIfAbsent(game, k -> new TreeMap<>())
                    .merge(name, qty, Integer::sum);
        }

        System.out.println("\n게임별 아이템 수량 요약");
        for (String game : gameItemMap.keySet()) {
            System.out.println("게임: " + game);
            for (Map.Entry<String, Integer> entry : gameItemMap.get(game).entrySet()) {
                System.out.printf(" - %s : %d개\n", entry.getKey(), entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        lupangSHOPConsoleApp app = new lupangSHOPConsoleApp();
        app.run();
    }
}
