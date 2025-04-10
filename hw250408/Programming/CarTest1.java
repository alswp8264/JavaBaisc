package hw250408.Programming;

public class CarTest1 {
	public static void main(String[] args) {
        Car1 c1 = new Car1("red");
        Car1 c2 = new Car1("blue");
        Car1 c3 = new Car1("RED");

        System.out.printf("자동차 수 : %d, 빨간색 자동차 수 : %d\n", Car1.getNumOfCar(), Car1.getNumOfRedCar());
    }

}
