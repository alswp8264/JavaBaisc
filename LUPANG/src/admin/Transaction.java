package admin;

import java.time.LocalDateTime;
import item.ItemVO;
import member.Member;

public class Transaction {
    private int id;
    private ItemVO item;
    private Member buyer;
    private Member seller;
    private String status; // RESERVED → DELIVERED → COMPLETED 대신 문자열
    private LocalDateTime createdAt;

    public Transaction(int id, ItemVO item, Member buyer) {
        this.id = id;
        this.item = item;
        this.buyer = buyer;
        this.seller = item.getSeller();
        this.status = "예약됨"; // 기존 ItemStatus.RESERVED 대신 문자열 사용
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public Member getBuyer() {
        return buyer;
    }

    public ItemVO getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status != null && !status.isBlank()) {
            this.status = status.trim(); // "배송중", "완료" 등으로 설정
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Member getSeller() {
        return seller;
    }
}
