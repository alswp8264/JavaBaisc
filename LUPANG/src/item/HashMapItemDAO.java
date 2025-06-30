package item;

import java.io.*;
import java.util.*;

import member.Member;

public class HashMapItemDAO implements ItemDAO {

    protected Map<Integer, ItemVO> itemDB = new HashMap<>();
    protected int itemSeq = 1001;

    // 📌 생성자에서 로딩
    public HashMapItemDAO() {
        loadItems();
    }

    @Override
    public void insert(ItemVO item) {
        if (item.getItemId() == 0 || itemDB.containsKey(item.getItemId())) {
            while (itemDB.containsKey(itemSeq)) {
                itemSeq++;
            }
            item.setItemId(itemSeq++);
        }
        itemDB.put(item.getItemId(), item);
    }

    @Override
    public ItemVO selectById(int itemId) {
        return itemDB.get(itemId);
    }

    @Override
    public List<ItemVO> selectAll() {
        return new ArrayList<>(itemDB.values());
    }

    @Override
    public void update(ItemVO item) {
        int itemId = item.getItemId();
        if (itemDB.containsKey(itemId)) {
            itemDB.put(itemId, item);
        }
    }

    @Override
    public boolean delete(int itemId) {
        return itemDB.remove(itemId) != null;
    }

    @Override
    public List<ItemVO> selectBySeller(Member seller) {
        List<ItemVO> result = new ArrayList<>();
        for (ItemVO item : itemDB.values()) {
            if (item.getSeller() != null && item.getSeller().equals(seller)) {
                result.add(item);
            }
        }
        return result;
    }

    public ItemVO selectById(String itemId) {
        try {
            return selectById(Integer.parseInt(itemId));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public void saveItems() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("itemDB.obj"))) {
            oos.writeObject(new ArrayList<>(itemDB.values()));
            System.out.println("✅ 아이템 목록이 itemDB.obj 파일에 저장되었습니다.");
        } catch (Exception e) {
            System.out.println("❌ 아이템 저장 중 오류 발생: " + e.getMessage());
        }
    }

    // ✅ 이거 없으면 다 소용없음!
    @Override
    public void loadItems() {
        File file = new File("itemDB.obj");
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            List<ItemVO> loaded = (List<ItemVO>) ois.readObject();
            for (ItemVO item : loaded) {
                itemDB.put(item.getItemId(), item);
                if (item.getItemId() >= itemSeq) {
                    itemSeq = item.getItemId() + 1;
                }
            }
            System.out.println("📥 itemDB.obj 로딩 완료 (총 " + itemDB.size() + "개)");
        } catch (Exception e) {
            System.out.println("❌ 아이템 로딩 중 오류 발생: " + e.getMessage());
        }
    }
}
