package ui;

import java.util.List;

public class PlaylistPrinter {

    public void printForward(List<String> songs) {
        System.out.println("\nPlaylist (Forward):");
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        songs.forEach(song -> System.out.print(song + " -> "));
        System.out.println("null");
    }

    public void printBackward(List<String> songs) {
        System.out.println("\nPlaylist (Backward):");
        if (songs.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }
        songs.forEach(song -> System.out.print(song + " -> "));
        System.out.println("null");
    }
}
