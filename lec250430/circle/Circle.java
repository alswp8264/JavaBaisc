package lec250430.circle;

public class Circle  extends CircleType {

	public static final double PI = 3.14;
	protected double radius;
	
	public Circle(double radius) {
		this.radius = radius;
	}
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}


	public double getArea() {
		return PI * radius * radius;
	}

}
