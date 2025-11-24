public class t12 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.print(i + " ");
            }
        });
        Thread thread2 = new Thread(() -> {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.print(c + " ");
            }
        });
        thread1.start();
        thread2.start();
    }
}