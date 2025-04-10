package hw250408.Programming;

public class Car1 {
	 private String color;

	    private static int numOfCar = 0;
	    private static int numOfRedCar = 0;

	    public Car1(String color) {
	        this.color = color;
	        numOfCar++;
	        if (color.equalsIgnoreCase("red")) {
	            numOfRedCar++;
	        }
	    }

	    public static int getNumOfCar() {
	        return numOfCar;
	    }

	    public static int getNumOfRedCar() {
	        return numOfRedCar;
	    }

}
