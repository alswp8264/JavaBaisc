package hw250408.Programming;

public class GolfClub1 {
	 private int number;
	    private String type;
	    private boolean isStringType;

	    
	    public GolfClub1(int number) {
	        this.number = number;
	        this.isStringType = false;
	    }

	    
	    public GolfClub1(String type) {
	        this.type = type;
	        this.isStringType = true;
	    }

	    
	    public void print() {
	        if (isStringType) {
	            System.out.println(type + "입니다.");
	        } else {
	            System.out.println(number + "번 아이언입니다.");
	        }
	    }

}
