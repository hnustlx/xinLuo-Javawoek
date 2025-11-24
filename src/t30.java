import java.util.*;
abstract class Employee {
    protected String id;
    protected String name;
    protected double baseSalary;
    public Employee(String id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public abstract double calculateSalary();
    public String getEmployeeInfo() {
        return "ID:" + id + " 姓名:" + name + " 基础工资:" + baseSalary;
    }
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return Objects.equals(id, employee.id);
    }
    public int hashCode() {
        return Objects.hash(id);
    }
}
class FullTimeEmployee extends Employee {
    private double performanceBonus;
    public FullTimeEmployee(String id, String name, double baseSalary, double performanceBonus) {
        super(id, name, baseSalary);
        this.performanceBonus = performanceBonus;
    }
    public double calculateSalary() {
        return baseSalary + performanceBonus;
    }
    public String getEmployeeInfo() {
        return super.getEmployeeInfo() + " 绩效奖金:" + performanceBonus + " 实发工资:" + calculateSalary();
    }
}
class PartTimeEmployee extends Employee {
    private double hourlyRate;
    private double hoursWorked;
    public PartTimeEmployee(String id, String name, double baseSalary, double hourlyRate, double hoursWorked) {
        super(id, name, baseSalary);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
    public String getEmployeeInfo() {
        return super.getEmployeeInfo() + " 时薪:" + hourlyRate + " 工时:" + hoursWorked + " 实发工资:" + calculateSalary();
    }
}
class SalesEmployee extends Employee {
    private double salesAmount;
    public SalesEmployee(String id, String name, double baseSalary, double salesAmount) {
        super(id, name, baseSalary);
        this.salesAmount = salesAmount;
    }
    public double calculateSalary() {
        return baseSalary + salesAmount * 0.05;
    }
    public String getEmployeeInfo() {
        return super.getEmployeeInfo() + " 销售额:" + salesAmount + " 实发工资:" + calculateSalary();
    }
}
class Company {
    private List<Employee> employees = new ArrayList<>();
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    public double calculateTotalSalary() {
        return employees.stream().mapToDouble(Employee::calculateSalary).sum();
    }
    public void displayEmployeesBySalary() {
        Collections.sort(employees, (e1, e2) -> Double.compare(e1.calculateSalary(), e2.calculateSalary()));
        employees.forEach(e -> System.out.println(e.getEmployeeInfo()));
    }
    public void findSalaryExtremes() {
        Employee min = employees.stream().min((e1, e2) -> Double.compare(e1.calculateSalary(), e2.calculateSalary())).orElse(null);
        Employee max = employees.stream().max((e1, e2) -> Double.compare(e1.calculateSalary(), e2.calculateSalary())).orElse(null);
        System.out.println("最低工资员工: " + (min != null ? min.getEmployeeInfo() : "无"));
        System.out.println("最高工资员工: " + (max != null ? max.getEmployeeInfo() : "无"));
    }
}
public class t30 {
    public static void main(String[] args) {
        Company company = new Company();
        company.addEmployee(new FullTimeEmployee("001", "张三", 5000, 2000));
        company.addEmployee(new PartTimeEmployee("002", "李四", 0, 50, 80));
        company.addEmployee(new SalesEmployee("003", "王五", 3000, 50000));
        System.out.println("总工资: " + company.calculateTotalSalary());
        System.out.println("按工资排序:");
        company.displayEmployeesBySalary();
        System.out.println("工资极值:");
        company.findSalaryExtremes();
    }
}