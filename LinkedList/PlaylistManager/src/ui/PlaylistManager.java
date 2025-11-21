package ui;

import playlist.PlaylistImpl;
import playlist.PlaylistOperations;
import java.util.Scanner;

public class PlaylistManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PlaylistOperations playlist = new PlaylistImpl();
        PlaylistPrinter printer = new PlaylistPrinter();

        while (true) {
            System.out.println("\n--- Music Playlist Manager ---");
            System.out.println("1. Add Song at Beginning");
            System.out.println("2. Add Song at End");
            System.out.println("3. Delete Song");
            System.out.println("4. Display Playlist (Forward)");
            System.out.println("5. Display Playlist (Backward)");
            System.out.println("6. Total Songs");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter song title: ");
                    playlist.addAtBeginning(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter song title: ");
                    playlist.addAtEnd(sc.nextLine());
                }
                case 3 -> {
                    System.out.print("Enter song title to delete: ");
                    boolean removed = playlist.deleteSong(sc.nextLine());
                    System.out.println(removed ? "Song deleted." : "Song not found.");
                }
                case 4 -> printer.printForward(playlist.getForwardList());
                case 5 -> printer.printBackward(playlist.getBackwardList());
                case 6 -> System.out.println("Total songs: " + playlist.size());
                case 7 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
