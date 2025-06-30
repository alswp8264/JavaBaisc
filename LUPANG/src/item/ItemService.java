package item;

import java.util.List;

public interface ItemService {
    boolean registerItem(ItemVO item);
    List<ItemVO> listItems();
    ItemVO getItemDetail(String itemId);
    boolean updateItemStatus(String itemId, String status);
    boolean isValidStatus(String status);

}