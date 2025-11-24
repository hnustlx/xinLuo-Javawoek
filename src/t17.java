import java.util.*;
public class t17 {
    static final int CAPACITY = 5;
    static Queue<Integer> queue = new LinkedList<>();
    public static void main(String[] args) {
        Thread producer = new Thread(()->{
            int value = 0;
            while(true){
                synchronized(queue){
                    while(queue.size()==CAPACITY){
                        try{queue.wait();}catch(InterruptedException e){}
                    }
                    queue.offer(value);
                    System.out.println("生产: "+value);
                    value++;
                    queue.notifyAll();
                    try{Thread.sleep(500);}catch(InterruptedException e){}
                }
            }
        });
        Thread consumer = new Thread(()->{
            while(true){
                synchronized(queue){
                    while(queue.isEmpty()){
                        try{queue.wait();}catch(InterruptedException e){}
                    }
                    int value = queue.poll();
                    System.out.println("消费: "+value);
                    queue.notifyAll();
                    try{Thread.sleep(1000);}catch(InterruptedException e){}
                }
            }
        });
        producer.start();
        consumer.start();
    }
}