package ui;

import manager.*;
import service.ChatService;

import java.util.List;
import java.util.Scanner;
import model.Message;


public class ChatApp {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        StatusManager statusManager = new StatusManager();
        MessageQueueManager queueManager = new MessageQueueManager();
        ChatHistoryManager historyManager = new ChatHistoryManager();
        UndoManager undoManager = new UndoManager();

        ChatService chat = new ChatService(userManager, statusManager, historyManager, queueManager, undoManager);

        Scanner sc = new Scanner(System.in);
        Integer currentUserId = null;
        String currentUsername = null;

        System.out.println("=== Mini Chat Backend (DS Based) ===");

        menu:
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Send message");
            System.out.println("4. View history");
            System.out.println("5. Undo last message");
            System.out.println("6. Logout");
            System.out.println("7. Exit");
            System.out.print("Choice: ");
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;
            int choice;
            try { choice = Integer.parseInt(line); } catch (Exception e) { System.out.println("Invalid"); continue; }

            switch (choice) {
                case 1 -> {
                    System.out.print("Choose username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Choose password: ");
                    String p = sc.nextLine();
                    int uid = chat.register(u, p);
                    if (uid >= 0) System.out.println("Registered. Your userId = " + uid);
                    else System.out.println("Registration failed (username may exist).");
                }
                case 2 -> {
                    if (currentUserId != null) { System.out.println("Already logged in as " + currentUsername); break; }
                    System.out.print("Username: ");
                    String u = sc.nextLine().trim();
                    System.out.print("Password: ");
                    String p = sc.nextLine();
                    int uid = chat.login(u, p);
                    if (uid >= 0) {
                        currentUserId = uid;
                        currentUsername = u;
                        System.out.println("Login success. userId=" + uid);
                    } else {
                        System.out.println("Login failed.");
                    }
                }
                case 3 -> {
                    if (currentUserId == null) { System.out.println("Please login first."); break; }
                    System.out.print("Send to (userId): ");
                    String to = sc.nextLine().trim();
                    int toId;
                    try { toId = Integer.parseInt(to); } catch (Exception e) { System.out.println("Invalid id"); break; }
                    System.out.print("Message: ");
                    String msg = sc.nextLine();
                    chat.sendMessage(currentUserId, toId, msg);
                    System.out.println("Message sent (queued & processed).");
                }
                case 4 -> {
                    List<Message> history = chat.getHistory();
                    if (history.isEmpty()) System.out.println("No messages yet.");
                    else {
                        System.out.println("Chat history:");
                        for (Message m : history) System.out.println(m);
                    }
                }
                case 5 -> {
                    boolean ok = chat.undoLastMessage();
                    System.out.println(ok ? "Undo successful." : "Nothing to undo or mismatch.");
                }
                case 6 -> {
                    if (currentUserId != null) {
                        chat.logout(currentUserId);
                        System.out.println("Logged out " + currentUsername);
                        currentUserId = null;
                        currentUsername = null;
                    } else {
                        System.out.println("No user logged in.");
                    }
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    break menu;
                }
                default -> System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
