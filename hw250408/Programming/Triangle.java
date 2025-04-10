package hw250408.Programming;

public class Triangle {
	  private double width;
	    private double height;

	 
	    public Triangle(double width, double height) {
	        this.width = width;
	        this.height = height;
	    }

	
	    public double findArea() {
	        return (width * height) / 2.0;
	    }
	}


