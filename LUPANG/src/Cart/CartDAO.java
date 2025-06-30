package Cart;

import java.util.List;

import item.ItemVO;

public interface CartDAO {
    boolean insertCartItem(CartItemVO item);
    CartItemVO selectCartItem(int itemId);
    List<CartItemVO> selectAllCartItem();
    boolean deleteCartItem(int itemId);
    boolean clear();
	boolean addToCart(ItemVO item);

}
