interface Shape {
    double calculateArea();
    double calculatePerimeter();
    String getShapeName();
    void displayInfo();
}
class Circle implements Shape {
    private double radius;
    public Circle(double radius) {
        this.radius = radius;
    }
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    public String getShapeName() {
        return "圆形";
    }
    public void displayInfo() {
        System.out.println("图形:" + getShapeName() + " 半径:" + radius + " 面积:" + String.format("%.2f", calculateArea()) + " 周长:" + String.format("%.2f", calculatePerimeter()));
    }
}
class Rectangle implements Shape {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public double calculateArea() {
        return width * height;
    }
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    public String getShapeName() {
        return "矩形";
    }
    public void displayInfo() {
        System.out.println("图形:" + getShapeName() + " 宽:" + width + " 高:" + height + " 面积:" + calculateArea() + " 周长:" + calculatePerimeter());
    }
}
class Triangle implements Shape {
    private double a, b, c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double calculateArea() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double calculatePerimeter() {
        return a + b + c;
    }
    public String getShapeName() {
        return "三角形";
    }
    public void displayInfo() {
        System.out.println("图形:" + getShapeName() + " 边长:" + a + "," + b + "," + c + " 面积:" + String.format("%.2f", calculateArea()) + " 周长:" + calculatePerimeter());
    }
}
class ShapeManager {
    public static void testShapes() {
        Shape[] shapes = {
                new Circle(5),
                new Rectangle(4, 6),
                new Triangle(3, 4, 5)
        };
        for (Shape shape : shapes) {
            shape.displayInfo();
        }
    }
}
public class t29 {
    public static void main(String[] args) {
        ShapeManager.testShapes();
    }
}