package Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import item.ItemDAO;
import item.ItemVO;

public class CartMenu {
    private final Scanner sc;
    private final CartService cartService;
    private final ItemDAO itemDAO;

    public CartMenu(Scanner sc, CartService cartService, ItemDAO itemDAO) {
        this.sc = sc;
        this.cartService = cartService;
        this.itemDAO = itemDAO;
    }

    public void run() {
        while (true) {
            System.out.println("\n==== 🛒 장바구리 메뉴 ====");
            System.out.println("0. 장바구니에 담기");
            System.out.println("1. 장바구니 목록 보기");
            System.out.println("2. 아이템 제거");
            System.out.println("3. 장바구니 비우기");
            System.out.println("4. 결제하기 (전체 / 선택)");
            System.out.println("5. 이전화면으로 돌아가기");
            System.out.print(">> 선택: ");
            String input = sc.nextLine();

            switch (input) {
                case "0" -> addToCart();
                case "1" -> viewItems();
                case "2" -> removeItem();
                case "3" -> clearCart();
                case "4" -> checkout();
                case "5" -> {
                    System.out.println("🔙 구매자 메뉴로 돌아간다.");
                    return;
                }
                default -> System.out.println("❌ 잘못된 입력입니다.");
            }
        }
    }

    private void addToCart() {
        try {
            System.out.print("장바구니에 담을 아이템 ID 입력: ");
            int itemId = Integer.parseInt(sc.nextLine());

            ItemVO item = itemDAO.selectById(itemId);
            if (item == null) {
                System.out.println("❌ 해당 아이템이 존재하지 않습니다.");
                return;
            }

            if (!(item.getStatus().equals("LISTED") || item.getStatus().equals("AVAILABLE"))) {
                System.out.printf("❌ [%s] 상태의 아이템은 장바구니에 담을 수 없습니다.\n", item.getStatus());
                return;
            }

            boolean added = cartService.addItemToCart(item);
            if (added) {
                System.out.printf("✅ [%d] %s 아이템이 장바구니에 추가되었습니다.\n", item.getItemId(), item.getName());
            } else {
                System.out.printf("🔁 [%d] %s 아이템은 이미 장바구니에 있습니다.\n", item.getItemId(), item.getName());
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ 숫자로 입력해주세요.");
        }
    }

    private void viewItems() {
        List<CartItemVO> items = cartService.listCartItems();

        items.removeIf(ci -> {
            String status = ci.getItem().getStatus();
            return status.equals("SOLD") || status.equals("CANCELLED");
        });

        if (items.isEmpty()) {
            System.out.println("🛒 장바구니가 비어 있습니다.");
        } else {
            System.out.println("\n📋 장바구니 목록:");
            System.out.printf("%-4s| %-20s| %-8s| %-6s| %-10s| %-6s\n",
                    "ID", "아이템명", "단가", "수량", "총가격", "상태");
            System.out.println("----|----------------------|----------|--------|------------|--------");

            for (CartItemVO ci : items) {
                ItemVO item = ci.getItem();
                String statusLabel = item.getStatus();
                System.out.printf("%-4d| %-20s| %,8d원| %,6d개| %,10d원| %-6s\n",
                        item.getItemId(), item.getName(), item.getPrice(), ci.getQuantity(),
                        ci.getTotalPrice(), statusLabel);
            }

            System.out.println("--------------------------------------------------------------");
            System.out.printf("💰 총 합계: %,d원\n", cartService.getTotalPrice());
        }
    }

    private void removeItem() {
        try {
            System.out.print("삭제할 아이템 ID 입력: ");
            int itemId = Integer.parseInt(sc.nextLine());
            boolean removed = cartService.removeCartItem(itemId);
            if (removed) {
                System.out.println("✅ 아이템이 장바구니에서 제거되었습니다.");
            } else {
                System.out.println("❌ 해당 아이템이 장바구니에 없습니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ 숫자로 입력해주세요.");
        }
    }

    private void clearCart() {
        cartService.clearCart();
        System.out.println("🧹 장바구니를 모두 비우였습니다.");
    }

    private void checkout() {
        try {
            System.out.println("1. 전체 결제");
            System.out.println("2. 선택 결제");
            System.out.print(">> 선택: ");
            String input = sc.nextLine();

            if (input.equals("1")) {
                int total = cartService.checkoutAllItems(itemDAO);
                System.out.printf("✅ 전체 결제 완료! 총 %,d원\n", total);
            } else if (input.equals("2")) {
                System.out.print("결제할 아이템 ID를 ,로 구분해 입력: ");
                String[] parts = sc.nextLine().split(",");
                List<Integer> ids = new ArrayList<>();
                for (String s : parts) {
                    ids.add(Integer.parseInt(s.trim()));
                }
                int total = cartService.checkoutSelectedItems(ids, itemDAO);
                System.out.printf("✅ 선택 결제 완료! 총 %,d원\n", total);
            } else {
                System.out.println("❌ 잘못된 입력입니다.");
            }
        } catch (Exception e) {
            System.out.println("❌ 결제 처리 중 오류 발생: " + e.getMessage());
        }
    }
}
