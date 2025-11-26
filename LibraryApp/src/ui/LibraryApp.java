package ui;

import catalog.LibraryCatalog;
import model.Book;
import service.LibraryService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LibraryCatalog catalog = new LibraryCatalog();
        LibraryService service = new LibraryService(catalog);

        while (true) {

            System.out.println("\n--- Library Catalog System ---");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by ISBN");
            System.out.println("4. List All Books");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1 -> {
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Title: ");
                    String title = sc.nextLine();

                    System.out.print("Authors (comma-separated: ");
                    String authorInput = sc.nextLine();
                    List<String> authors = Arrays.stream(authorInput.split(","))
                            .map(String::trim)
                            .toList();

                    System.out.print("Year: ");
                    int year = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Category: ");
                    String category = sc.nextLine();

                    System.out.print("Copies: ");
                    int copies = sc.nextInt();
                    sc.nextLine();

                    boolean added = service.addNewBook(isbn, title, authors, year, category, copies);
                    System.out.println(added ? "Book added successfully!" : "Book already exists!");
                }

                case 2 -> {
                    System.out.print("Enter ISBN to remove: ");
                    String isbn = sc.nextLine();
                    boolean removed = service.removeBook(isbn);
                    System.out.println(removed ? "Book removed!" : "Book not found!");
                }

                case 3 -> {
                    System.out.print("Enter ISBN to search: ");
                    String isbn = sc.nextLine();
                    Book book = service.findByISBN(isbn);
                    System.out.println(book != null ? book : "No book found.");
                }

                case 4 -> {
                    System.out.println("\nAll Books:");
                    service.getAllBooks().forEach(System.out::println);
                }

                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
