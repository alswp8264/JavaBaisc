package hw250408.Programming;

public class TriangleTest1 {
	public static void main(String[] args) {
		Triangle1 t1 = new Triangle1(10.0, 5.0); // 넓이: 25.0
		Triangle1 t2 = new Triangle1(5.0, 10.0); // 넓이: 25.0
		Triangle1 t3 = new Triangle1(6.0, 5.0); // 넓이: 15.0

		System.out.println("t1과 t2의 넓이가 같은가? " + t1.isSameArea(t2)); // true
		System.out.println("t1과 t3의 넓이가 같은가? " + t1.isSameArea(t3)); // false

	}

}
