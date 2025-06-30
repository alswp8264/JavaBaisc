// 📁 app/InputUtil.java
package app;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner sc = new Scanner(System.in);

    public static String safeReadString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ 값을 입력해주세요.");
        }
    }

    public static int safeReadInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ 잘못 된 입력 방식입니다");
            }
        }
    }
    public static String safeReadNullableString(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String input = sc.nextLine();
                if (input != null && !input.trim().isEmpty()) {
                    return input.trim();
                }
            } catch (Exception e) {
                // 무시 후 반복
            }
            System.out.println("❌ 해당 게임에 대한 정보가 없습니다 .");
        }
    }

}
