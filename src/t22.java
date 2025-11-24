import java.util.*;
public class t22 {
    public static void main(String[] args) {
        String str = "hello world";
        HashMap<Character, Integer> map = new HashMap<>();
        for(char c:str.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        System.out.println(map);
    }
}