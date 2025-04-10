package hw250408.challenge;

public class Printer {
    private int numOfPapers;

    public Printer(int numOfPapers) {
        this.numOfPapers = numOfPapers;
    }

    public void print(int amount) {
        if (numOfPapers == 0) {
            
        } else if (amount > numOfPapers) {
            
            numOfPapers = 0;
        } else {
            numOfPapers -= amount;
          
        }
    }

    public int getNumOfPapers() {
        return numOfPapers;
    }
}
	


