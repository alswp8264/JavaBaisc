package challenge;

import java.util.ArrayList;
import java.util.List;

public class Pro1 {
   public static void main(String[] args) {
	List<String> animals = new ArrayList<>();
	animals.add("갈매기");
	animals.add("나비");
	animals.add("다람쥐");
	animals.add("라마");
	
	for (String animal : animals) {
		if (animal.length()== 2) {
		System.out.println(animal);
	}
	}
}
}
