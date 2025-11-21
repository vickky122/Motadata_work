package tabs;

import model.TabNode;

public class BrowserTabs {

    private TabNode head, tail;
    private int size;

    public void openTab(String title) {
        TabNode newTab = new TabNode(title);

        if (head == null) {
            head = tail = newTab;
        } else {
            tail.next = newTab;
            newTab.prev = tail;
            tail = newTab;
        }
        size++;
        System.out.println("Opened new tab: " + title);
    }

    public void closeTab(String title) {
        if (head == null) {
            System.out.println("No tabs open.");
            return;
        }

        TabNode curr = head;

        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) {

                if (curr == head) {
                    head = curr.next;
                    if (head != null) head.prev = null;
                } else if (curr == tail) {
                    tail = curr.prev;
                    if (tail != null) tail.next = null;
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }

                size--;
                System.out.println("Closed tab: " + title);
                return;
            }
            curr = curr.next;
        }

        System.out.println("Tab not found: " + title);
    }

    public void showTabs() {
        if (head == null) {
            System.out.println("No tabs open.");
            return;
        }

        TabNode temp = head;
        System.out.println("\nOpen Tabs:");
        while (temp != null) {
            System.out.print(temp.title + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void searchTab(String title) {
        TabNode temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                System.out.println("Tab found: " + temp.title);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Tab not found.");
    }

    public int getTabCount() {
        return size;
    }
}
