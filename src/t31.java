import java.util.*;
import java.io.*;

class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    private String isbn;
    private String title;
    private String author;
    private int stock;
    public Book(String isbn, String title, String author, int stock) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public String toString() {
        return "ISBN:" + isbn + " 书名:" + title + " 作者:" + author + " 库存:" + stock;
    }
}

class Library implements Serializable {
    private static final long serialVersionUID = 1L;
    private ArrayList<Book> books = new ArrayList<>();
    public void addBook(Book book) {
        books.add(book);
        sortBooks();
    }
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("暂无图书");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }
    public boolean borrowBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                if (book.getStock() > 0) {
                    book.setStock(book.getStock() - 1);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
    public boolean returnBook(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setStock(book.getStock() + 1);
                return true;
            }
        }
        return false;
    }
    private void sortBooks() {
        Collections.sort(books, Comparator.comparing(Book::getIsbn));
    }
    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(books);
        }
    }
    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) throws IOException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            books = (ArrayList<Book>) in.readObject();
            sortBooks();
        } catch (ClassNotFoundException e) {
            throw new IOException("反序列化失败: " + e.getMessage());
        }
    }
}

public class t31 {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.添加图书 2.查看所有图书 3.借书 4.还书 5.保存到文件 6.从文件加载 0.退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("输入ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("输入书名: ");
                    String title = scanner.nextLine();
                    System.out.print("输入作者: ");
                    String author = scanner.nextLine();
                    System.out.print("输入库存: ");
                    int stock = scanner.nextInt();
                    library.addBook(new Book(isbn, title, author, stock));
                    break;
                case 2:
                    library.displayAllBooks();
                    break;
                case 3:
                    System.out.print("输入要借的ISBN: ");
                    String borrowIsbn = scanner.nextLine();
                    if (library.borrowBook(borrowIsbn)) {
                        System.out.println("借书成功");
                    } else {
                        System.out.println("借书失败，库存不足或图书不存在");
                    }
                    break;
                case 4:
                    System.out.print("输入要还的ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    if (library.returnBook(returnIsbn)) {
                        System.out.println("还书成功");
                    } else {
                        System.out.println("还书失败，图书不存在");
                    }
                    break;
                case 5:
                    try {
                        library.saveToFile("books.dat");
                        System.out.println("保存成功");
                    } catch (IOException e) {
                        System.out.println("保存失败");
                    }
                    break;
                case 6:
                    try {
                        library.loadFromFile("books.dat");
                        System.out.println("加载成功");
                    } catch (IOException e) {
                        System.out.println("加载失败");
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}