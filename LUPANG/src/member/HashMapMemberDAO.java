package member;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapMemberDAO implements MemberDAO {

    protected Map<String, Member> memberDB = new HashMap<>();
    protected int memberSeq = 111;

    @Override
    public boolean insertMember(Member member) {
        if (memberDB.containsKey(member.getId())) {
            return false; // 중복 ID
        }

        member.setMemberNo(memberSeq++);
        member.setRegDate(new Date());
        memberDB.put(member.getId(), member);
        return true;
    }

    @Override
    public Member selectMember(String id) {
        return memberDB.get(id);
    }

    @Override
    public List<Member> selectAllMembers() {
        return new ArrayList<>(memberDB.values());
    }

    @Override
    public boolean updateMember(Member newMember) {
        return memberDB.put(newMember.getId(), newMember) != null;
    }

    @Override
    public boolean deleteMember(String id) {
        return memberDB.remove(id) != null;
    }
}
