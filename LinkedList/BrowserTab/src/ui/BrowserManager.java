package ui;

import tabs.BrowserTabs;

import java.util.Scanner;

public class BrowserManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BrowserTabs tabs = new BrowserTabs();

        while (true) {
            System.out.println("\n--- Browser Tabs Manager ---");
            System.out.println("1. Open New Tab");
            System.out.println("2. Close Tab");
            System.out.println("3. Show All Tabs");
            System.out.println("4. Search a Tab");
            System.out.println("5. Total Tabs");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter tab title: ");
                    tabs.openTab(sc.nextLine());
                }
                case 2 -> {
                    System.out.print("Enter tab title to close: ");
                    tabs.closeTab(sc.nextLine());
                }
                case 3 -> tabs.showTabs();
                case 4 -> {
                    System.out.print("Enter title to search: ");
                    tabs.searchTab(sc.nextLine());
                }
                case 5 -> System.out.println("Total tabs: " + tabs.getTabCount());
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
