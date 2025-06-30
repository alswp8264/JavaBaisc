package item;

import java.io.Serializable;
import java.util.Date;
import member.Member;

public class ItemVO implements Serializable {
    private int itemId;
    private String name;
    private String game;
    private int price;
    private Member seller;
    private Member buyer;
    private String status;
    private int quantity;
    private String server;
    private Date regDate;
    private String gameName;

    // 🔒 상태 코드 상수 (내부 저장용)
    public static final String STATUS_LISTED = "LISTED";
    public static final String STATUS_AVAILABLE = "AVAILABLE";
    public static final String STATUS_RESERVED = "RESERVED";
    public static final String STATUS_SOLD = "SOLD";
    public static final String STATUS_CANCELLED = "CANCELLED";

    // 🛠 기본 생성자 및 판매용 생성자
    public ItemVO(String name, String game, String server, int price, Member seller) {
        this.name = name;
        this.game = game;
        this.server = server;
        this.price = price;
        this.seller = seller;
        this.status = STATUS_LISTED;
        this.regDate = new Date();
        this.quantity = 1;
    }

    // 📦 Getter/Setter
    public int getItemId() { return itemId; }
    public void setItemId(int itemId) { this.itemId = itemId; }

    public String getName() { return name; }
    public void setName(String name) {
        if (name != null && !name.isBlank()) this.name = name.trim();
    }

    public String getGame() { return game; }
    public void setGame(String game) {
        if (game != null && !game.isBlank()) this.game = game.trim();
    }

    public int getPrice() { return price; }
    public void setPrice(int price) {
        if (price > 0) this.price = price;
    }

    public Member getSeller() { return seller; }
    public void setSeller(Member seller) { this.seller = seller; }

    public Member getBuyer() { return buyer; }
    public void setBuyer(Member buyer) { this.buyer = buyer; }

    public String getStatus() { return status; }

    // 🎯 한글 상태를 내부 코드로 변환
    public void setStatus(String status) {
        if (status == null || status.isBlank()) {
            this.status = STATUS_AVAILABLE;
            return;
        }

        switch (status.trim()) {
            case "판매 중" -> this.status = STATUS_LISTED;
            case "예약 중" -> this.status = STATUS_RESERVED;
            case "거래 완료" -> this.status = STATUS_SOLD;
            case "판매 중지" -> this.status = STATUS_CANCELLED;
            case "구매가능" -> this.status = STATUS_AVAILABLE;
            default -> this.status = status.toUpperCase();  // 직접 코드 입력 가능
        }
    }

    // 🧾 내부 상태 코드 → 사용자용 한글 라벨
    public String getStatusLabel() {
        return switch (status) {
            case STATUS_LISTED -> "판매 중";
            case STATUS_RESERVED -> "예약 중";
            case STATUS_SOLD -> "거래 완료";
            case STATUS_CANCELLED -> "판매 중지";
            case STATUS_AVAILABLE -> "구매 가능";
            default -> "알 수 없음";
        };
    }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity >= 0) this.quantity = quantity;
    }

    public String getServer() { return server; }
    public void setServer(String server) { this.server = server; }

    public Date getRegDate() { return regDate; }
    public void setRegDate(Date regDate) { this.regDate = regDate; }

    public String getGameName() { return this.gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }

    @Override
    public String toString() {
        return String.format(
            "[%d] %s (%s) - %,d원 / 판매자: %s / 구매자: %s / 상태: %s",
            itemId, name, game, price,
            seller != null ? seller.getNickname() : "미상",
            buyer != null ? buyer.getNickname() : "-",
            getStatusLabel() // 상태 라벨로 출력
        );
    }
}
