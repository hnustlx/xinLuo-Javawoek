import java.util.*;
public class t17 {
    static final int CAPACITY = 5;
    static Queue<Integer> queue = new LinkedList<>();
    static volatile boolean running = true;
    public static void main(String[] args) {
        Thread producer = new Thread(()->{
            int value = 0;
            while(running){
                synchronized(queue){
                    if(!running) break;
                    while(queue.size()==CAPACITY && running){
                        try{queue.wait();}catch(InterruptedException e){if(!running) break;}
                    }
                    if(!running) break;
                    queue.offer(value);
                    System.out.println("生产: "+value);
                    value++;
                    queue.notifyAll();
                    try{Thread.sleep(500);}catch(InterruptedException e){if(!running) break;}
                }
            }
            System.out.println("生产者结束");
        });
        Thread consumer = new Thread(()->{
            while(running || !queue.isEmpty()){
                synchronized(queue){
                    if(!running && queue.isEmpty()) break;
                    while(queue.isEmpty() && running){
                        try{queue.wait();}catch(InterruptedException e){if(!running) break;}
                    }
                    if(!running && queue.isEmpty()) break;
                    int value = queue.poll();
                    System.out.println("消费: "+value);
                    queue.notifyAll();
                    try{Thread.sleep(1000);}catch(InterruptedException e){if(!running) break;}
                }
            }
            System.out.println("消费者结束");
        });
        producer.start();
        consumer.start();
        new Thread(()->{
            try{
                Thread.sleep(10000);
                running = false;
                synchronized(queue){
                    queue.notifyAll();
                }
                producer.join();
                consumer.join();
                System.out.println("程序结束");
                System.exit(0);
            }catch(InterruptedException e){}
        }).start();
    }
}