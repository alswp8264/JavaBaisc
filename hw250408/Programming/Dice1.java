package hw250408.Programming;
import java.util.Random;
public class Dice1 {
	   private Random rand;

	    public Dice1() {
	        rand = new Random();
	    }

	    public int roll() {
	        return rand.nextInt(6) + 1;  // 1~6
	    }

}
