package Cart;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import item.ItemVO;

public class HashMapCartDAO implements CartDAO {

    private Map<Integer, CartItemVO> cartMap = new LinkedHashMap<>();

    @Override
    public boolean insertCartItem(CartItemVO item) {
        int itemId = item.getItem().getItemId();
        if (cartMap.containsKey(itemId)) {
            cartMap.get(itemId).increment();
            return false;
        }
        cartMap.put(itemId, item);
        return true;
    }

    @Override
    public CartItemVO selectCartItem(int itemId) {
        return cartMap.get(itemId);
    }

    @Override
    public List<CartItemVO> selectAllCartItem() {
        return new ArrayList<>(cartMap.values());
    }

    @Override
    public boolean deleteCartItem(int itemId) {
        return cartMap.remove(itemId) != null;
    }

    @Override
    public boolean clear() {
        cartMap.clear();
        return true;
    }

	@Override
	public boolean addToCart(ItemVO item) {
		// TODO Auto-generated method stub
		return false;
	}
}
