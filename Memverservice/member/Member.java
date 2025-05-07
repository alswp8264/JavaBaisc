package member;

public class Member {
    
	private int memberNo;
	private String id;
	private String password;
	private String username;
	
	public  Member (int memverNo, String id, String password, String username) {
	 this.memberNo = memberNo;
	 this. id = id;
	 this. password = password;
	 this. username = username;
	 
}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}


	

	@Override
	public String toString() {
		return "[ memberNo=" + memberNo + ", id=" + id + ", password=" + password + ", username=" + username
				+ "]";
	}
	
}
