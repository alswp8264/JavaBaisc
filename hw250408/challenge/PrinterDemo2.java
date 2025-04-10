package hw250408.challenge;

public class PrinterDemo2 {
	   public static void main(String[] args) {
	        Printer2 p = new Printer2(20, true);
	        p.print(25);          // 양면
	        p.setDuplex(false);   // 단면으로 변경
	        p.print(10);          // 단면
	    }

}
