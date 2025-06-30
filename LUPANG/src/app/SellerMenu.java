package app;

import static app.InputUtil.safeReadInt;
import static app.InputUtil.safeReadString;

import java.util.List;
import java.util.Scanner;

import item.ItemDAO;
import item.ItemVO;
import member.Member;

/**
 * 판매자 전용 메뉴 클래스
 * - 등록 아이템 관리, 수정, 중지, 거래 상태 확인 기능 제공
 */
public class SellerMenu {
    private final Member loggedMember;     // 현재 로그인한 사용자
    private final ItemDAO itemDAO;         // 아이템 데이터 접근 객체
    private final Scanner sc;              // 입력 스캐너

    public SellerMenu(Member loggedMember, ItemDAO itemDAO, Scanner sc) {
        this.loggedMember = loggedMember;
        this.itemDAO = itemDAO;
        this.sc = sc;
    }

    /**
     * 판매자 메뉴 실행
     */
    public void run() {
        String[] menu = {
            "이전화면으로 돌아가기",
            "내 등록 아이템 보기",
            "아이템 등록",
            "아이템 수정",
            "판매 중지",
            "예약/완료 상태 아이템 보기"
        };

        int sel;
        do {
            sel = selectMenu(menu);
            switch (sel) {
                case 1 -> viewMyItems();
                case 2 -> registerItem();
                case 3 -> updateItem();
                case 4 -> stopSelling();
                case 5 -> viewReservedOrSold();
                case 0 -> {
                    System.out.println("이전화면으로 돌아갑니다.");
                    return;
                }
            }
        } while (true);
    }

    // 메뉴 출력 및 사용자 선택
    private int selectMenu(String[] menu) {
        System.out.println("\n==== 판매자 메뉴 ====");
        for (int i = 1; i < menu.length; i++) {
            System.out.printf("%d. %s%n", i, menu[i]);
        }
        System.out.println("0. " + menu[0]);
        return safeReadInt(">> 선택: ");
    }

    // 등록 아이템 조회
    private void viewMyItems() {
        List<ItemVO> list = itemDAO.selectBySeller(loggedMember);

        if (list == null || list.isEmpty()) {
            System.out.println("등록된 아이템이 없습니다.");
            return;
        }

        list.forEach(System.out::println);
    }

    // 아이템 신규 등록
    private void registerItem() {
        String name = safeReadString("아이템 이름: ");
        String game = safeReadString("게임 이름: ");
        String server = safeReadString("서버 이름: ");
        int price = safeReadInt("가격: ");
        ItemVO item = new ItemVO(name, game, server, price, loggedMember);
        item.setStatus("판매 중");
        itemDAO.insert(item);
        System.out.println("아이템이 등록되었습니다.");
    }

    // 아이템 수정
    private void updateItem() {
        int id = safeReadInt("수정할 아이템 ID: ");
        ItemVO item = itemDAO.selectById(id);
        if (item == null || !loggedMember.equals(item.getSeller())) {
            System.out.println("해당 아이템을 수정할 수 없습니다.");
            return;
        }
        String name = safeReadString("새 아이템 이름: ");
        int price = safeReadInt("새 가격: ");
        item.setName(name);
        item.setPrice(price);
        itemDAO.update(item);
        System.out.println("아이템이 수정되었습니다.");
    }

    // 판매 중지 처리
    private void stopSelling() {
        int id = safeReadInt("판매 중지할 아이템 ID: ");
        ItemVO item = itemDAO.selectById(id);
        if (item == null || !loggedMember.equals(item.getSeller())) {
            System.out.println("해당 아이템을 판매 중지할 수 없습니다.");
            return;
        }
        item.setStatus("판매 중지");
        itemDAO.update(item);
        System.out.println("판매 중지되었습니다.");
    }

    // 예약/완료 상태 아이템 조회
    private void viewReservedOrSold() {
        List<ItemVO> list = itemDAO.selectBySeller(loggedMember);

        if (list == null || list.isEmpty()) {
            System.out.println("예약 중이거나 거래 완료된 아이템이 없습니다.");
            return;
        }

        boolean found = false;
        for (ItemVO item : list) {
            if ("예약 중".equals(item.getStatus()) || "거래 완료".equals(item.getStatus())) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) System.out.println("예약 중이거나 거래 완료된 아이템이 없습니다.");
    }
}
