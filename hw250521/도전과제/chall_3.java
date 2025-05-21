package challenge;

import java.util.LinkedHashMap;
import java.util.Map;

public class chall_3 {
    public static void main(String[] args) {
      
        Map<String, String> slangMap = new LinkedHashMap<>();
        slangMap.put("head", "대가빠리");
        slangMap.put("teacher", "썜");
        slangMap.put("cat", "꼬네이");
        slangMap.put("aunt", "dkwlao");
        slangMap.put("noodle", "국시");
        slangMap.put("child", "얼라");

    
        for (Map.Entry<String, String> entry : slangMap.entrySet()) {
            System.out.print(entry.getKey() + "=" + entry.getValue() + " ");
        }
        System.out.println();

      
        for (String value : slangMap.values()) {
            String replaced = value.equals("dkwlao") ? "아지매" : value;
            System.out.print(replaced + " ");
        }
        System.out.println();
    }
}
