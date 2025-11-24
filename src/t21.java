import java.net.*;
import java.io.*;
public class t21 {
    public static void main(String[] args) throws Exception {
        new Thread(()->{
            try(ServerSocket serverSocket=new ServerSocket(8080);
                Socket socket=serverSocket.accept();
                BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                System.out.println("收到消息: "+in.readLine());
            }catch(Exception e){}
        }).start();
        Thread.sleep(1000);
        try(Socket socket=new Socket("localhost",8080);
            PrintWriter out=new PrintWriter(socket.getOutputStream(),true)){
            out.println("Hello Server");
        }catch(Exception e){}
    }
}