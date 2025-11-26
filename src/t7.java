public class t7 {
    public static void main(String[] args){
        BankAccount1 account = new BankAccount1(100);
        account.deposit(2400);
        account.checkBanlace();
        account.deposit(500);
        account.checkBanlace();
    }
}
class BankAccount1 {
    private double banlance;
    BankAccount1(){
        banlance = 0.0;
    }
    BankAccount1(double balance){
        if(balance>=0){
            this.banlance = balance;
        }else{
            System.out.println("输入余额不能为负数");
        }
    }
    public void deposit(double amount){
        if(amount>0){
            banlance+=amount;
            System.out.println("存款成功！存入金额：" + amount);
        }else{
            System.out.println("存款失败！存款金额不能为负数或零");
        }
    }
    public void withdraw(double amount){
        if(banlance-amount>=0){
            banlance-=amount;
            System.out.println("取款成功！取出金额："+amount);
        }else{
            System.out.println("取款失败！余额不足");
        }
    }
    public void checkBanlace(){
        System.out.println("当前余额为："+banlance);
    }

}