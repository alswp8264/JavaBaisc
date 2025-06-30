package Cart;

import java.util.List;
import member.Member;
import item.ItemDAO;
import item.ItemVO;

public class CartServiceImpl implements CartService {

    private CartDAO cartDAO;

    public CartServiceImpl(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }

    @Override
    public boolean addItemToCart(ItemVO item) {
        return cartDAO.insertCartItem(new CartItemVO(item, 1));
    }

    @Override
    public CartItemVO getCartItemInfo(int itemId) {
        return cartDAO.selectCartItem(itemId);
    }

    @Override
    public List<CartItemVO> listCartItems() {
        return cartDAO.selectAllCartItem();
    }

    @Override
    public boolean isCartEmpty() {
        return cartDAO.selectAllCartItem().isEmpty();
    }

    @Override
    public boolean removeCartItem(int itemId) {
        return cartDAO.deleteCartItem(itemId);
    }

    @Override
    public boolean clearCart() {
        return cartDAO.clear();
    }

    @Override
    public int getTotalPrice() {
        return cartDAO.selectAllCartItem()
                      .stream()
                      .mapToInt(CartItemVO::getTotalPrice)
                      .sum();
    }

    // 전체 결제 (buyer 없는 버전 → buyer null로 처리)
    @Override
    public int checkoutAllItems(ItemDAO itemDAO) {
        return checkoutAllItems(itemDAO, null);
    }

    // 선택 결제 (buyer 없는 버전 → buyer null로 처리)
    @Override
    public int checkoutSelectedItems(List<Integer> ids, ItemDAO itemDAO) {
        return checkoutSelectedItems(ids, itemDAO, null);
    }

    // 전체 결제 (buyer 포함)
    @Override
    public int checkoutAllItems(ItemDAO itemDAO, Member buyer) {
        int total = 0;
        for (CartItemVO cartItem : listCartItems()) {
            ItemVO item = cartItem.getItem();
            String status = item.getStatus();
            if ("AVAILABLE".equals(status) || "LISTED".equals(status)) {
                item.setStatus("RESERVED");
                item.setBuyer(buyer);
                itemDAO.update(item);
                total += item.getQuantity() * item.getPrice();
                removeCartItem(item.getItemId());
            }
        }
        return total;
    }

    // 선택 결제 (buyer 포함)
    @Override
    public int checkoutSelectedItems(List<Integer> ids, ItemDAO itemDAO, Member buyer) {
        int total = 0;
        for (int id : ids) {
            CartItemVO cartItem = getCartItemInfo(id);
            if (cartItem == null) continue;
            ItemVO item = cartItem.getItem();
            String status = item.getStatus();
            if ("AVAILABLE".equals(status) || "LISTED".equals(status)) {
                item.setStatus("RESERVED");
                item.setBuyer(buyer);
                itemDAO.update(item);
                total += cartItem.getQuantity() * item.getPrice();
                removeCartItem(id);
            }
        }
        return total;
    }
}
