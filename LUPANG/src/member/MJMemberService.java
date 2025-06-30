package member;

import java.util.List;

public class MJMemberService implements MemberService {

	private MemberDAO memberDAO;

	public MJMemberService(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public boolean registMember(Member member) {
		return memberDAO.insertMember(member);
	}

	@Override
	public List<Member> listMembers() {
		return memberDAO.selectAllMembers();
	}

	@Override
	public Member detailMemberInfo(String id) {
		return memberDAO.selectMember(id);
	}

	@Override
	public Member login(String id, String password) {
		Member member = memberDAO.selectMember(id);
		if (member != null && member.getPassword().equals(password)) {
			return member;
		}
		return null;
	}

	@Override
	public boolean updatePassword(String id, String oldPassword, String newPassword) {
		Member member = memberDAO.selectMember(id);
		if (member == null) return false;

		if (member.getPassword().equals(oldPassword)) {
			member.setPassword(newPassword);
			return memberDAO.updateMember(member);
		}
		return false;
	}

	@Override
	public boolean addMemberInfo(String id, String mobile, String email, String address) {
		Member member = memberDAO.selectMember(id);
		if (member == null) return false;

		return memberDAO.updateMember(member);
	}

	@Override
	public boolean removeMember(String id, String password) {
		Member member = memberDAO.selectMember(id);
		if (member == null) return false;
		if (!member.getPassword().equals(password)) return false;
		return memberDAO.deleteMember(id);
	}

	@Override
	public boolean deleteById(String id) {
		// ✅ 회원 강제 탈퇴나 비밀번호 없이 삭제 시 사용
		return memberDAO.deleteMember(id);
	}

	@Override
	public void update(Member member) {
		// ✅ 일반 회원 정보 수정 시 사용
		memberDAO.updateMember(member);
	}

	@Override
	public boolean isNicknameTaken(String newNickname) {
		List<Member> members = memberDAO.selectAllMembers();
		for (Member m : members) {
			if (m.getNickname().equalsIgnoreCase(newNickname)) {
				return true;
			}
		}
		return false;
	}
}
