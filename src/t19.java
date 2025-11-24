import java.lang.reflect.*;
public class t19 {
    public static void main(String[] args) throws Exception {
        Class<?> cls = String.class;
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        System.out.println("方法:");
        for(Method method:methods){
            System.out.println(method.getName());
        }
        System.out.println("属性:");
        for(Field field:fields){
            System.out.println(field.getName());
        }
    }
}