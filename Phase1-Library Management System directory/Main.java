import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Book [Title=" + title + ", Author=" + author + ", ISBN=" + isbn + ", Available=" + isAvailable + "]";
    }
}

class User {
    private String name;
    private String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User [Name=" + name + ", User ID=" + userId + "]";
    }
}


class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean borrowBook(String isbn, String userId) {
        Book book = findBookByIsbn(isbn);
        if (book != null && book.isAvailable()) {
            book.setAvailable(false);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book != null && !book.isAvailable()) {
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void listBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    public void listUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System");
            System.out.println("1. Add Book to Library");
            System.out.println("2. Add User to Library");
            System.out.println("3. Borrow Book from Library");
            System.out.println("4. Return Book to Library");
            System.out.println("5. List of all Books");
            System.out.println("6. List of all Users");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter title of the book: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author name of book: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter book ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                    break;
                case 2:
                    System.out.print("Enter user name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String userId = scanner.nextLine();
                    library.addUser(new User(name, userId));
                    break;
                case 3:
                    System.out.print("Enter book ISBN: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Enter user ID: ");
                    String borrowUserId = scanner.nextLine();
                    if (library.borrowBook(borrowIsbn, borrowUserId)) {
                        System.out.println("Book borrowed successfully.");
                    } else {
                        System.out.println("Book not available.");
                    }
                    break;
                case 4:
                    System.out.print("Enter book ISBN: ");
                    String returnIsbn = scanner.nextLine();
                    if (library.returnBook(returnIsbn)) {
                        System.out.println("Book returned successfully.");
                    } else {
                        System.out.println("Invalid book ISBN.");
                    }
                    break;
                case 5:
                    library.listBooks();
                    break;
                case 6:
                    library.listUsers();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
