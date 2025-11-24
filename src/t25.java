import java.util.concurrent.*;
public class t25 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i=0;i<5;i++){
            final int taskId=i;
            executor.submit(()->{
                System.out.println("任务"+taskId+"执行");
            });
        }
        executor.shutdown();
    }
}