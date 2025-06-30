package item;

import java.util.List;

public class MJitemService implements ItemService {

    private ItemDAO itemDAO;

    public MJitemService(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    @Override
    public boolean registerItem(ItemVO item) {
        itemDAO.insert(item);
        return true;
    }

    @Override
    public List<ItemVO> listItems() {
        return itemDAO.selectAll();
    }

    @Override
    public ItemVO getItemDetail(String itemId) {
        try {
            int id = Integer.parseInt(itemId);
            return itemDAO.selectById(id);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public boolean updateItemStatus(String itemId, String status) {
        try {
            int id = Integer.parseInt(itemId);
            ItemVO item = itemDAO.selectById(id);
            if (item == null || status == null || status.isBlank()) return false;

            // ✅ enum 없이 문자열 직접 저장
            item.setStatus(status.toUpperCase().trim());
            itemDAO.update(item);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
        @Override
        public boolean isValidStatus(String status) {
            return status != null && (
                status.equals("판매 중") ||
                status.equals("예약 중") ||
                status.equals("거래 완료") ||
                status.equals("판매 중지")
            );
        }
    
    
    
}
