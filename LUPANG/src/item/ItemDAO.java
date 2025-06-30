package item;

import java.util.List;
import member.Member;

public interface ItemDAO {

    // ✅ 기본 CRUD
    void insert(ItemVO item);                     // 아이템 등록
    ItemVO selectById(int itemId);                // ID로 아이템 조회
    List<ItemVO> selectAll();                     // 전체 아이템 목록 조회
    void update(ItemVO item);                     // 아이템 정보 갱신
    boolean delete(int itemId);                   // ID로 아이템 삭제
    List<ItemVO> selectBySeller(Member m);        // 판매자별 아이템 목록

    // ✅ 편의 기능
    default ItemVO selectById(String itemId) {
        try {
            return selectById(Integer.parseInt(itemId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // ✅ 저장 기능 (본문 없음)
    void saveItems();  // 구현은 HashMapItemDAO에서
	void loadItems();
}
