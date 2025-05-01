package progam06;

public interface Human {

	 static void echo() {
	        System.out.println("야호!!!");
	    }

	    //Worker 클래스는 수정할 수 없다는 조건으로 인해 default라는 예약어를 붙여 구현 메서드를 만든다.
	    default void print() {
	        System.out.println("인간입니다.");
	    }
	    void eat();
	}
