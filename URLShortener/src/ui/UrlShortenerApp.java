package ui;

import repository.InMemoryUrlRepository;
import repository.UrlRepository;
import service.UrlShortenerService;
import util.Base62Encoder;
import util.IdGenerator;

import java.util.Scanner;

public class UrlShortenerApp {

    public static void main(String[] args) {

        UrlRepository repository = new InMemoryUrlRepository();
        IdGenerator idGenerator = new IdGenerator();
        Base62Encoder encoder = new Base62Encoder();

        String baseDomain = "https://motadata/";

        UrlShortenerService service = new UrlShortenerService(repository, idGenerator, encoder, baseDomain);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- URL Shortener ---");
            System.out.println("1. Add new URL");
            System.out.println("2. Retrieve original URL");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String rawChoice = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(rawChoice.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter long URL: ");
                    String longUrl = scanner.nextLine();
                    try {
                        String shortUrl = service.createShortUrl(longUrl);
                        System.out.println("Short URL: " + shortUrl);
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 2 -> {
                    System.out.print("Enter short URL or short code: ");
                    String shortInput = scanner.nextLine();
                    try {
                        String original = service.getOriginalUrl(shortInput);
                        if (original == null) {
                            System.out.println("No mapping found for: " + shortInput);
                        } else {
                            System.out.println("Original URL: " + original);
                        }
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
