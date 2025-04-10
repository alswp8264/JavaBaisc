package hw250408.Programming;

public class LineTest1 {
	  public static void main(String[] args) {
	        Line1 a = new Line1(1);
	        Line1 b = new Line1(1);

	        System.out.println(a.isSameLine(b)); // true (내용 비교)
	        System.out.println(a == b);          // false (객체 참조 비교)
	    }
}
