package member;

import java.util.List;

public interface MemberService {

	boolean registMember(Member member);                     // 회원 가입
	List<Member> listMembers();                              // 전체 회원 목록
	Member detailMemberInfo(String id);                      // 회원 상세 정보
	Member login(String id, String password);                // 로그인
	boolean updatePassword(String id, String oldPassword, String newPassword);  // 비밀번호 변경
	boolean addMemberInfo(String id, String mobile, String email, String address); // 부가정보 추가
	boolean removeMember(String id, String password);          // 일반 회원 탈퇴
	boolean deleteById(String id);                             // 관리자 강제 탈퇴
	void update(Member loggedMember);
	boolean isNicknameTaken(String newNickname);

	// 아래 메서드는 보통 필요 없음. 필요한 경우 명확히 정의
	// Member[] findAll(); // 🔸 가능하면 삭제, 아니면 MemberVO[]로 수정
}
