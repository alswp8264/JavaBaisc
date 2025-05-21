package challenge;

import java.util.HashMap;
import java.util.Map;

public class Pro5 {

	  public static void main(String[] args) {
	        Map<String, String> animals = new HashMap<>();
	        animals.put("호랑이", "tiger");
	        animals.put("표범", "leopard");
	        animals.put("사자", "lion");

	        System.out.println("변경 전 : " + animals);

	       
	        animals.replaceAll((k, v) -> v.toUpperCase());

	        System.out.println("변경 후 : " + animals);
	    }
	}
