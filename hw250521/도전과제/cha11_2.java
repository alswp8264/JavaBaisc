package challenge;

import java.util.HashMap;
import java.util.Map;

public class cha11_2 {
    public static void main(String[] args) {
        Map<String, String> slangMap = new HashMap<>();
        slangMap.put("head", "대가빠리");
        slangMap.put("teacher", "썜");
        slangMap.put("cat", "꼬네이");
        slangMap.put("aunt", "아지매");
        slangMap.put("noodle", "국시");
        slangMap.put("child", "얼라");

        
        for (Map.Entry<String, String> entry : slangMap.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println(); 
    }
}
