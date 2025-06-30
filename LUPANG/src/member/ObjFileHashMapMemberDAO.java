package member;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class ObjFileHashMapMemberDAO extends HashMapMemberDAO implements FileMemberDB {

	private String dataFilename = DATA_FILE + ".obj";
	public ObjFileHashMapMemberDAO() {
		loadMembers();
	}
	
	@Override
	public void saveMembers() {
		try (
				FileOutputStream fos = new FileOutputStream(dataFilename);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
					
			) {
				oos.writeObject(memberDB);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		
	}

	@Override
	public void loadMembers() {
	    try (
	        FileInputStream fis = new FileInputStream(dataFilename);
	        ObjectInputStream ois = new ObjectInputStream(fis);
	    ) {
	        memberDB = (Map<String, Member>) ois.readObject();
	        for (Member member : memberDB.values()) {
	            if (member.getMemberNo() >= memberSeq)
	                memberSeq = member.getMemberNo() + 1;
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("[DB로딩] " + dataFilename + "가 없습니다.");
	        memberDB = new java.util.HashMap<>(); // ✅ 파일 없을 때 초기화 추가
	    } catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	        memberDB = new java.util.HashMap<>(); // ✅ 예외 발생 시에도 안전하게 초기화
	    }
	}

	
	@Override
	public boolean insertMember(Member member) {
		boolean result = super.insertMember(member);
		if (result) saveMembers();
		return result;
	}
	
	@Override
	public boolean updateMember(Member newMember) {
		boolean result = super.updateMember(newMember);
		if (result) saveMembers();
		return result;
	}
	
	@Override
	public boolean deleteMember(String id) {
		boolean result = super.deleteMember(id);
		if (result) saveMembers();
		return result;
	}

}
