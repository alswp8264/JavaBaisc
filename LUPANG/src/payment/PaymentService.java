package payment;

import item.ItemVO;
import member.Member;

public class PaymentService {

    // 결제 처리
    public boolean processPayment(Member buyer, ItemVO item) {
        int price = item.getPrice();

        if (buyer.getPoint() < price) {
            System.out.printf("❌ 포인트 부족: 현재 %dP, 필요 %dP\n", buyer.getPoint(), price);
            return false;
        }

        // 포인트 차감
        buyer.setPoint(buyer.getPoint() - price);

        // 구매 내역에 추가 (선택적)
        buyer.addPurchasedItem(item);

        System.out.printf("✅ [%s] 아이템 %dP로 결제 성공! 남은 포인트: %dP\n",
                item.getName(), price, buyer.getPoint());

        return true;
    }
}
