package MyPageMenu;

import static app.InputUtil.*; // static import 필요

import java.util.Scanner;
import member.Member;
import member.MemberService;

public class MyPageMenu {
    private final MemberService ms;
    private final Scanner sc;
    private final Member loggedMember;
    private boolean loggedOut = false;  // ✅ 추가: 로그아웃 상태 추적용

    public MyPageMenu(MemberService ms, Scanner sc, Member loggedMember) {
        this.ms = ms;
        this.sc = sc;
        this.loggedMember = loggedMember;
    }

    public void run() {
        while (!loggedOut) {
            System.out.println("\n=== 👤 내 정보 관리 ===");
            System.out.println("1. 닉네임 변경");
            System.out.println("2. 비밀번호 변경");
            System.out.println("3. 회원 탈퇴");
            System.out.println("4. 포인트 충전");
            System.out.println("0. 이전 메뉴로 돌아가기");
            System.out.print(">> 선택: ");
            String input = sc.nextLine().trim();

            switch (input) {
                case "1" -> changeNickname();
                case "2" -> changePassword();
                case "3" -> {
                    if (deleteAccount()) {
                        loggedOut = true;  // ✅ 탈퇴 성공 시 로그아웃 처리
                    }
                }
                case "4" -> chargePoint();
                case "0" -> { return; }
                default -> System.out.println("❌ 잘못된 입력입니다.");
            }
        }
    }

    // ✅ 추가: 로그아웃 상태 외부에서 확인하는 getter
    public boolean isLoggedOut() {
        return loggedOut;
    }

    private void changeNickname() {
        System.out.print("새 닉네임 입력: ");
        String newNickname = sc.nextLine().trim();

        if (newNickname.isEmpty()) {
            System.out.println("❌ 닉네임이 비어있습니다.");
            return;
        }

        if (newNickname.equals(loggedMember.getNickname())) {
            System.out.println("⚠️ 현재 닉네임과 동일합니다.");
            return;
        }

        if (ms.isNicknameTaken(newNickname)) {
            System.out.println("❌ 이미 사용 중인 닉네임입니다.");
            return;
        }

        loggedMember.setUsername(newNickname);
        ms.update(loggedMember);
        System.out.println("✅ 닉네임이 변경되었습니다.");
    }

    private void changePassword() {
        System.out.print("현재 비밀번호: ");
        String current = sc.nextLine();

        if (!loggedMember.getPassword().equals(current)) {
            System.out.println("❌ 비밀번호가 일치하지 않습니다.");
            return;
        }

        System.out.print("새 비밀번호 입력: ");
        String newPassword = sc.nextLine().trim();

        if (newPassword.isEmpty()) {
            System.out.println("❌ 비밀번호가 비어있습니다.");
            return;
        }

        if (newPassword.equals(current)) {
            System.out.println("⚠️ 현재 비밀번호와 동일합니다. 다른 비밀번호를 입력하세요.");
            return;
        }

        loggedMember.setPassword(newPassword);
        ms.update(loggedMember);
        System.out.println("✅ 비밀번호가 변경되었습니다.");
    }

    private boolean deleteAccount() {
        System.out.print("정말 탈퇴하시겠습니까? (yes 입력 시 탈퇴): ");
        String confirm = sc.nextLine();

        if ("yes".equalsIgnoreCase(confirm)) {
            try {
                boolean result = ms.deleteById(loggedMember.getId());
                if (result) {
                    System.out.println("✅ 탈퇴가 완료되었습니다.");
                    return true;
                } else {
                    System.out.println("❌ 탈퇴 실패. 관리자에게 문의하세요.");
                }
            } catch (Exception e) {
                System.out.println("❌ 시스템 오류로 탈퇴에 실패했습니다. 관리자에게 문의하세요.");
            }
        } else {
            System.out.println("🚫 탈퇴가 취소되었습니다.");
        }
        return false;
    }

    private void chargePoint() {
        System.out.print("💰 충전할 포인트 금액 입력: ");
        String input = sc.nextLine().trim();
        try {
            int amount = Integer.parseInt(input);
            if (amount <= 0) {
                System.out.println("❌ 1 이상 입력해주세요.");
                return;
            }

            int before = loggedMember.getPoint();
            loggedMember.setPoint(before + amount);
            ms.update(loggedMember);
            System.out.printf("✅ 포인트 %d원 충전 완료! 현재 잔액: %d원\n", amount, loggedMember.getPoint());
        } catch (NumberFormatException e) {
            System.out.println("❌ 숫자를 정확히 입력해주세요.");
        }
    }
}
