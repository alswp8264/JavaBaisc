// member/MemberDAO.java
package member;

import java.util.List;

public interface MemberDAO {
    boolean insertMember(Member member);
    Member selectMember(String id);
    List<Member> selectAllMembers();
    boolean updateMember(Member member);
    boolean deleteMember(String id);
}
