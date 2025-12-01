import java.util.*;
public class t17 {
    private static final int BUFFER_SIZE = 5;
    private static final List<Integer> buffer = new ArrayList<>();
    private static boolean running = true;
    public static void main(String[] args) {
        Thread producer = new Thread(new Producer(), "生产者");
        Thread consumer = new Thread(new Consumer(), "消费者");
        producer.start();
        consumer.start();
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                synchronized (buffer) {
                    running = false;
                    buffer.notifyAll();
                }
                producer.join();
                consumer.join();
                System.out.println("程序结束");
            } catch (InterruptedException e) {}
        }).start();
    }
    static class Producer implements Runnable {
        private int itemCount = 0;
        @Override
        public void run() {
            while (true) {
                synchronized (buffer) {
                    if (!running && buffer.isEmpty()) break;
                    while (buffer.size() == BUFFER_SIZE && running) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            if (!running) break;
                        }
                    }
                    if (!running) break;
                    buffer.add(itemCount);
                    System.out.println("生产: " + itemCount + " (缓冲区:" + buffer.size() + ")");
                    itemCount++;
                    buffer.notifyAll();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        if (!running) break;
                    }
                }
            }
            System.out.println("生产者结束");
        }
    }
    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (buffer) {
                    if (!running && buffer.isEmpty()) break;
                    while (buffer.isEmpty() && running) {
                        try {
                            buffer.wait();
                        } catch (InterruptedException e) {
                            if (!running) break;
                        }
                    }
                    if (!running && buffer.isEmpty()) break;
                    int item = buffer.remove(0);
                    System.out.println("消费: " + item + " (缓冲区:" + buffer.size() + ")");
                    buffer.notifyAll();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        if (!running) break;
                    }
                }
            }
            System.out.println("消费者结束");
        }
    }
}