import java.util.Scanner;
class NegativeNumberException extends Exception{
    public NegativeNumberException(String message){
        super(message);
    }
}
public class t9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            double num = sc.nextDouble();
            if(num<0){
                throw new NegativeNumberException("错误：输入的数字是负数！");
            }
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }finally{
            sc.close();
        }
    }
}