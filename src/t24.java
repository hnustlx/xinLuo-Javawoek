import java.util.*;
import java.io.*;
public class t24 {
    public static void main(String[] args) throws IOException {
        // 先创建配置文件
        Properties props = new Properties();
        props.setProperty("database.url", "localhost:3306");
        props.setProperty("database.username", "admin");
        props.setProperty("database.password", "123456");
        props.store(new FileOutputStream("config.properties"), "配置文件");

        // 读取配置文件
        Properties readProps = new Properties();
        readProps.load(new FileInputStream("config.properties"));
        for(String key:readProps.stringPropertyNames()){
            System.out.println(key+": "+readProps.getProperty(key));
        }
    }
}