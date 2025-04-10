package hw250408.Programming;

public class Line1 {
	  private int length;

	    // 생성자
	    public Line1(int length) {
	        this.length = length;
	    }

	    // 길이 비교 메서드
	    public boolean isSameLine(Line1 other) {
	        return this.length == other.length;
	    }

}
