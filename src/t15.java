import java.util.*;
public class t15 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.print("输入数字个数: ");
        int n = sc.nextInt();
        for(int i=0;i<n;i++){
            list.add(sc.nextInt());
        }
        HashSet<Integer> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        System.out.println(list);
        sc.close();
    }
}