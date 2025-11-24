import java.util.*;
import java.io.*;
import java.util.concurrent.*;
class BankAccount3 {
    private String accountId;
    private String username;
    private double balance;
    public BankAccount3(String accountId, String username, double balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = balance;
    }
    public String getAccountId() { return accountId; }
    public String getUsername() { return username; }
    public double getBalance() { return balance; }
    public synchronized void deposit(double amount) {
        balance += amount;
    }
    public synchronized boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public String toString() {
        return "账户ID:" + accountId + " 用户名:" + username + " 余额:" + balance;
    }
    public String toFileString() {
        return accountId + "," + username + "," + balance;
    }
    public static BankAccount3 fromFileString(String line) {
        String[] parts = line.split(",");
        return new BankAccount3(parts[0], parts[1], Double.parseDouble(parts[2]));
    }
}
class BankSystem {
    private HashMap<String, BankAccount3> accounts = new HashMap<>();
    public synchronized void createAccount(String accountId, String username, double initialBalance) {
        accounts.put(accountId, new BankAccount3(accountId, username, initialBalance));
    }
    public BankAccount3 getAccount(String accountId) {
        return accounts.get(accountId);
    }
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("暂无账户");
            return;
        }
        for (BankAccount3 account : accounts.values()) {
            System.out.println(account);
        }
    }
    public void saveToFile(String filename) throws IOException {
        synchronized (this) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (BankAccount3 account : accounts.values()) {
                    writer.println(account.toFileString());
                }
            }
        }
    }
    public void loadFromFile(String filename) throws IOException {
        synchronized (this) {
            accounts.clear();
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    BankAccount3 account = BankAccount3.fromFileString(line);
                    accounts.put(account.getAccountId(), account);
                }
            }
        }
    }
}
public class t32 {
    public static void main(String[] args) {
        BankSystem bank = new BankSystem();
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        while (true) {
            System.out.println("1.创建账户 2.存款 3.取款 4.查询余额 5.查看所有账户 6.保存到文件 7.从文件加载 0.退出");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("输入账户ID: ");
                    String accountId = scanner.nextLine();
                    System.out.print("输入用户名: ");
                    String username = scanner.nextLine();
                    System.out.print("输入初始余额: ");
                    double balance = scanner.nextDouble();
                    bank.createAccount(accountId, username, balance);
                    System.out.println("账户创建成功");
                    break;

                case 2:
                    System.out.print("输入账户ID: ");
                    String depositId = scanner.nextLine();
                    System.out.print("输入存款金额: ");
                    double depositAmount = scanner.nextDouble();
                    BankAccount3 depositAccount = bank.getAccount(depositId);
                    if (depositAccount != null) {
                        executor.submit(() -> {
                            depositAccount.deposit(depositAmount);
                            System.out.println("存款成功，当前余额: " + depositAccount.getBalance());
                        });
                    } else {
                        System.out.println("账户不存在");
                    }
                    break;

                case 3:
                    System.out.print("输入账户ID: ");
                    String withdrawId = scanner.nextLine();
                    System.out.print("输入取款金额: ");
                    double withdrawAmount = scanner.nextDouble();
                    BankAccount3 withdrawAccount = bank.getAccount(withdrawId);
                    if (withdrawAccount != null) {
                        executor.submit(() -> {
                            if (withdrawAccount.withdraw(withdrawAmount)) {
                                System.out.println("取款成功，当前余额: " + withdrawAccount.getBalance());
                            } else {
                                System.out.println("取款失败，余额不足");
                            }
                        });
                    } else {
                        System.out.println("账户不存在");
                    }
                    break;

                case 4:
                    System.out.print("输入账户ID: ");
                    String queryId = scanner.nextLine();
                    BankAccount3 queryAccount = bank.getAccount(queryId);
                    if (queryAccount != null) {
                        System.out.println("当前余额: " + queryAccount.getBalance());
                    } else {
                        System.out.println("账户不存在");
                    }
                    break;

                case 5:
                    bank.displayAllAccounts();
                    break;

                case 6:
                    try {
                        bank.saveToFile("accounts.txt");
                        System.out.println("保存成功");
                    } catch (IOException e) {
                        System.out.println("保存失败: " + e.getMessage());
                    }
                    break;

                case 7:
                    try {
                        bank.loadFromFile("accounts.txt");
                        System.out.println("加载成功");
                    } catch (IOException e) {
                        System.out.println("加载失败: " + e.getMessage());
                    }
                    break;

                case 0:
                    executor.shutdown();
                    try {
                        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                            executor.shutdownNow();
                        }
                    } catch (InterruptedException e) {
                        executor.shutdownNow();
                    }
                    return;

                default:
                    System.out.println("无效选择");
            }
        }
    }
}