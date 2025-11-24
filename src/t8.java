import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Product{
    private String  id;
    private String name;
    private double price;
    public Product(String id,String name,double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    @Override
    public String toString(){
        return String.format("商品Id: %s,商品名称 %s,价格：%.2f元",id,name,price);
    }
}
class ProductManager{
    private List<Product> productList;
    public ProductManager() {
        productList = new ArrayList<>();
    }
    public void addProduct(Product product){
        for(Product p : productList){
            if(product.getId().equals(p.getId())){
                System.out.println("错误：商品"+product.getId()+"已存在！");
                return;
            }
        }
        productList.add(product);
        System.out.println("商品添加成功："+product.getName());
    }
    public void displayAllProducts(){
        if(productList.isEmpty()){
            System.out.println("商品列表为空！");
            return;
        }
        System.out.println("\n=== 所有商品信息 === ");
        for(int i=0;i<productList.size();i++){
            System.out.println((i+1)+". "+productList.get(i));
        }
    }
    public void searchProductById(String id){
        for(Product product : productList){
            if(product.getId().equals(id)){
                System.out.println("查询结果为："+product);
                return;
            }
        }
        System.out.println("未找到ID为"+id+"的商品");
        return;
    }
    public void searchProductByName(String name){
        List<Product> result = new ArrayList<>();
        for(Product product:productList){
            if(product.getName().toLowerCase().contains(name.toLowerCase())){
                result.add(product);
            }
        }
        if(result.isEmpty()){
            System.out.println("未找到名称包含 ‘"+name+"的商品");
        }else{
            System.out.println("\n=== 查询结果 ===");
            for(int i=0;i<result.size();i++){
                System.out.println((i+1)+"."+result.get(i));
            }
        }
    }
    public int getProductCount(){
        return productList.size();
    }
}
public class t8 {
    private static Scanner scanner = new Scanner(System.in);
    private static ProductManager productManager = new ProductManager();
    public static void showMenu(){
        System.out.println("1. 添加商品");
        System.out.println("2. 显示所有商品");
        System.out.println("3. 根据ID查询商品");
        System.out.println("4. 根据名称查询商品");
        System.out.println("5. 显示商品总数");
        System.out.println("6. 退出系统");
    }
    private static void addProduct() {
        System.out.println("\n=== 添加商品 ===");
        System.out.print("请输入商品ID：");
        String id = scanner.nextLine();
        System.out.print("请输入商品名称：");
        String name = scanner.nextLine();
        double price = getDoubleInput("请输入商品价格：");
        Product product = new Product(id, name, price);
        productManager.addProduct(product);
    }
    private static void searchProductById() {
        System.out.print("请输入要查询的商品ID：");
        String id = scanner.nextLine();
        productManager.searchProductById(id);
    }
    private static void searchProductByName() {
        System.out.print("请输入要查询的商品名称（支持模糊查询）：");
        String name = scanner.nextLine();
        productManager.searchProductByName(name);
    }
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请输入一个整数！");
            }
        }
    }
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("输入无效，请输入一个有效的价格！");
            }
        }
    }
    private static void initializeSampleProducts() {
        productManager.addProduct(new Product("P001", "苹果手机", 5999.00));
        productManager.addProduct(new Product("P002", "华为笔记本", 6999.00));
        productManager.addProduct(new Product("P003", "小米电视", 3299.00));
        productManager.addProduct(new Product("P004", "联想平板", 1999.00));
        System.out.println("示例商品初始化完成！\n");
    }
    public static void main(String[] args) {
        System.out.println("=== 简单商品管理系统 ===");
        initializeSampleProducts();
        while (true) {
            showMenu();
            int choice = getIntInput("请选择操作：");
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    productManager.displayAllProducts();
                    break;
                case 3:
                    searchProductById();
                    break;
                case 4:
                    searchProductByName();
                    break;
                case 5:
                    System.out.println("当前商品总数：" + productManager.getProductCount());
                    break;
                case 6:
                    System.out.println("感谢使用商品管理系统，再见！");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效的选择，请重新输入！");
            }
            System.out.println();
        }
    }
}
