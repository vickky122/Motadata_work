package ui;

import java.util.List;

public class BookingPrinter {

    public void printAvailableSeats(List<Integer> seats) {
        System.out.println("\nAvailable Seats:");
        if (seats.isEmpty()) {
            System.out.println("No seats available.");
            return;
        }
        seats.forEach(seat -> System.out.print(seat + " "));
        System.out.println();
    }

    public void printBookedCount(int count) {
        System.out.println("Total booked seats: " + count);
    }

    public void printEmptyCount(int count) {
        System.out.println("Total empty seats: " + count);
    }
}
