import java.lang.reflect.Method;
class BankAccount1 {
    private double balance;
    public void deposit(double amount) {
        balance += amount;
        System.out.println("存款成功，当前余额：" + balance);
    }
}
public class t10 {
    public static void main(String[] args) throws Exception {
        BankAccount1 account = new BankAccount1();
        Class<?> cls = account.getClass();
        Method method = cls.getMethod("deposit", double.class);
        method.invoke(account, 1000.0);
    }
}