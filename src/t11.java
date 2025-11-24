import java.io.*;
import java.util.Scanner;
public class t11 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入内容: ");
        String content = sc.nextLine();
        FileWriter writer = new FileWriter("output.txt");
        writer.write(content);
        writer.close();
        FileReader reader = new FileReader("output.txt");
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = bufferedReader.readLine();
        System.out.println("文件内容: " + line);
        bufferedReader.close();
        reader.close();
        sc.close();
    }
}