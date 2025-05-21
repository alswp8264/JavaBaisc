package challenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class cha11 {
    public static void main(String[] args) {
        String[] state1 = { "서울", "워싱턴", "베이징", "마드리드", "파리", "런던" };
        List<String> state2 = new ArrayList<>(Arrays.asList(state1));

        state2.removeIf(s -> s.equals("런던"));

        for (String s : state2) {
            System.out.print(s + " ");
        }
        System.out.println(); 

        state2.add("런던");
        state2.sort((x, y) -> x.length() - y.length());

        String[] state3 = state2.toArray(new String[0]);

        for (String s : state3) {
            System.out.print(s + " ");
        }
        System.out.println(); 

        List<String> toRemove = Arrays.asList("파리", "베이징", "마드리드");
        state2.removeIf(toRemove::contains);

     
        for (String s : state2) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
