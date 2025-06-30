package Cart;

import java.util.List;

import item.ItemDAO;
import item.ItemVO;
import member.Member;

public interface CartService {
    boolean addItemToCart(ItemVO item);
    List<CartItemVO> listCartItems();
    boolean removeCartItem(int itemId);
    boolean clearCart();
    int getTotalPrice();
    int checkoutAllItems(ItemDAO itemDAO);
    int checkoutSelectedItems(List<Integer> ids, ItemDAO itemDAO);
	boolean isCartEmpty();
	CartItemVO getCartItemInfo(int itemId);
	int checkoutAllItems(ItemDAO itemDAO, Member buyer);
	int checkoutSelectedItems(List<Integer> ids, ItemDAO itemDAO, Member buyer);
}
