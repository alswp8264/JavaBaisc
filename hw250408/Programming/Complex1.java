package hw250408.Programming;

public class Complex1 {
	  private double real;
	    private double imaginary;

	    // 생성자 1 - 실수만
	    public Complex1(double real) {
	        this.real = real;
	        this.imaginary = 0.0;
	    }

	    // 생성자 2 - 실수와 허수
	    public Complex1(double real, double imaginary) {
	        this.real = real;
	        this.imaginary = imaginary;
	    }

	    // 출력 메서드
	    public void print() {
	        System.out.println(real + " + " + imaginary + "i");
	    }

}
