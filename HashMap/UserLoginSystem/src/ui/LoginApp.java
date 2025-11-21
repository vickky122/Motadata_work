package ui;

import repository.InMemoryUserRepository;
import repository.UserRepository;
import security.PasswordEncoder;
import security.SHA256PasswordEncoder;
import service.AuthService;

import java.util.Scanner;

public class LoginApp {

    public static void main(String[] args) {

        UserRepository repo = new InMemoryUserRepository();
        PasswordEncoder encoder = new SHA256PasswordEncoder();
        AuthService auth = new AuthService(repo, encoder);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- User Login System ---");
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter username: ");
                    String u = sc.nextLine();
                    System.out.print("Enter password: ");
                    String p = sc.nextLine();

                    boolean ok = auth.register(u, p);
                    System.out.println(ok ? "User registered successfully." :
                            "Registration failed (maybe user already exists).");
                }
                case 2 -> {
                    System.out.print("Enter username: ");
                    String u = sc.nextLine();
                    System.out.print("Enter password: ");
                    String p = sc.nextLine();

                    boolean ok = auth.login(u, p);
                    System.out.println(ok ? "Login successful!" : "Invalid credentials.");
                }
                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
