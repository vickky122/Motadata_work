package ui;

import java.util.List;

public class BufferPrinter {

    public void printBuffer(List<Integer> packets, int size, int capacity) {
        System.out.println("\nBuffer Content:");
        if (packets.isEmpty()) {
            System.out.println("[EMPTY]");
        } else {
            packets.forEach(val -> System.out.print(val + " "));
            System.out.println();
        }
        System.out.println("Size=" + size + " | Capacity=" + capacity);
    }
}
