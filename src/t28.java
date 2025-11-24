import java.util.*;
class Person {
    String name;
    int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString() {
        return name+":"+age;
    }
}
public class t28 {
    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Alice",25));
        list.add(new Person("Bob",20));
        list.add(new Person("Charlie",30));
        list.sort((p1,p2)->p1.age-p2.age);
        System.out.println(list);
    }
}