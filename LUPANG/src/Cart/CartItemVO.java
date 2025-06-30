package Cart;

import item.ItemVO;

public class CartItemVO {
    private ItemVO item;
    private int quantity;

    public CartItemVO(ItemVO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public ItemVO getItem() {
        return item;
    }

    public void setItem(ItemVO item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increment() {
        this.quantity++;
    }

    public void increase(int amount) {
        this.quantity += amount;
    }

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("[%s / %d개 / 총 %,d원]", 
            item.getName(), quantity, getTotalPrice());
    }
}
