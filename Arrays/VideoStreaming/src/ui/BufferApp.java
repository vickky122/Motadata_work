package ui;

import buffer.Buffer;
import buffer.DynamicArrayBuffer;
import service.BufferService;

import java.util.Scanner;

public class BufferApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Buffer buffer = new DynamicArrayBuffer(4);
        BufferService service = new BufferService(buffer);
        BufferPrinter printer = new BufferPrinter();

        while (true) {
            System.out.println("\n--- Video Packet Buffer ---");
            System.out.println("1. Add Packet");
            System.out.println("2. Get Packet by Index");
            System.out.println("3. Remove Last Packet");
            System.out.println("4. Show Buffer");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter packet value: ");
                    int p = sc.nextInt();
                    service.addPacket(p);
                }
                case 2 -> {
                    System.out.print("Enter index: ");
                    int idx = sc.nextInt();
                    if (!service.isIndexValid(idx)) {
                        System.err.println("Invalid index!");
                    } else {
                        System.out.println("Packet at index " + idx + ": " + service.getPacket(idx));
                    }
                }
                case 3 -> {
                    boolean removed = service.removePacket();
                    if (!removed)
                        System.err.println("Buffer is empty!");
                }
                case 4 -> printer.printBuffer(service.listPackets(), service.getSize(), service.getCapacity());
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.err.println("Invalid choice!");
            }
        }
    }
}
