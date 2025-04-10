package hw250408.Programming;

public class Triangle1 {
	 private double width;
	    private double height;

	    // 생성자
	    public Triangle1(double width, double height) {
	        this.width = width;
	        this.height = height;
	    }

	    // 넓이 계산 메서드
	    public double findArea() {
	        return (width * height) / 2.0;
	    }

	    // 넓이 비교 메서드
	    public boolean isSameArea(Triangle1 t) {
	        return this.findArea() == t.findArea();
	    }
	    }
