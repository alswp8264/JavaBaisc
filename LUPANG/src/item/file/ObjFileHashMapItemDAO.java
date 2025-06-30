package item.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Map;

import item.ItemVO;
import item.HashMapItemDAO;

public class ObjFileHashMapItemDAO extends HashMapItemDAO implements FileItemDB {

	private String dataFilename = DATA_FILE + ".obj";
	
	
	public ObjFileHashMapItemDAO() {
		loaditems();
	}
	
	@Override
	public void saveitems() {
		
		try (
			FileOutputStream fos = new FileOutputStream(dataFilename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
				
		) {
			oos.writeObject(itemDB);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void loaditems() {
	    try (
	        FileInputStream fis = new FileInputStream(dataFilename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	    ) {
	        itemDB = (Map<Integer, ItemVO>)ois.readObject();
	        if (itemDB != null && !itemDB.isEmpty()) {
	            itemSeq = Collections.max(itemDB.keySet()) + 1;
	        } else {
	            itemSeq = 1;  // 데이터 없으면 1부터 시작
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
	        itemSeq = 1; // 파일 없으면 1부터 시작
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public void insert(ItemVO item) {
		super.insert(item);
		 saveitems();
	}

	@Override
	public void update(ItemVO item) {
		super.update(item);
		saveitems();
		return ;
	}
	
	@Override
	public boolean delete(int itemId) {
		boolean result = super.delete(itemId);
		if (result) saveitems();
		return result;
	}

}
