import java.util.Scanner;
public class t5 {
    public static boolean isrunnian(int year){
        return (year%4==0&&year%100!=0)||(year%400==0);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int year = sc.nextInt();
        if(isrunnian(year)){
            System.out.println(year+"是闰年");
        }else{
            System.out.println(year+"不是闰年");
        }
        sc.close();
    }
}
