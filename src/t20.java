import java.io.*;
import java.util.*;
public class t20 {
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("input.txt");
        writer.write("Hello world this is a test file\n");
        writer.write("This is the second line with some words\n");
        writer.write("Java programming is interesting");
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
        String line;
        int wordCount = 0;
        while ((line = reader.readLine()) != null) {
            String[] words = line.split("\\s+");
            wordCount += words.length;
        }
        reader.close();
        System.out.println("单词数量: " + wordCount);
    }
}