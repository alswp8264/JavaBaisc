package member.exception;

public class NoMemberException  extends Exception{
   // 기본 생성자
	public NoMemberException() {
		
	}
	//  오류 메시지를 부모 객체의 생성자에게 전달하는 생성자
	public NoMemberException(String message) {
		super("[예외]없는 회원입니다 :"+ message );
	}
}
