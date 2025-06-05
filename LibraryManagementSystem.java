import java.util.*;

class Book {
    String title;
    String author;
    boolean isBorrowed;

    Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
    }

    void borrowBook() {
        isBorrowed = true;
    }

    void returnBook() {
        isBorrowed = false;
    }

    boolean isAvailable() {
        return !isBorrowed;
    }

    public String toString() {
        return title + " by " + author + (isBorrowed ? " [Borrowed]" : " [Available]");
    }
}

public class LibraryManagementSystem {
    private static final List<Book> books = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. View Available Books");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> borrowBook();
                case 4 -> returnBook();
                case 5 -> viewAvailableBooks();
                case 6 -> {
                    System.out.println("Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        books.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        books.forEach(System.out::println);
    }

    private static void borrowBook() {
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && book.isAvailable()) {
                book.borrowBook();
                System.out.println("You have borrowed: " + book.title);
                return;
            }
        }
        System.out.println("Book not available or already borrowed.");
    }

    private static void returnBook() {
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title) && !book.isAvailable()) {
                book.returnBook();
                System.out.println("You have returned: " + book.title);
                return;
            }
        }
        System.out.println("Book not found or not currently borrowed.");
    }

    private static void viewAvailableBooks() {
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books.");
        }
    }
}