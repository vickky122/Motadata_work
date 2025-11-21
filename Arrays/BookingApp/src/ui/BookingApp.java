package ui;

import repository.ArraySeatRepository;
import repository.SeatRepository;
import service.BookingService;

import java.util.Scanner;

public class BookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        SeatRepository repository = new ArraySeatRepository(300);
        BookingService service = new BookingService(repository);
        BookingPrinter printer = new BookingPrinter();

        while (true) {
            System.out.println("\n--- Flight Seat Booking System ---");
            System.out.println("1. Show Available Seats");
            System.out.println("2. Book a Seat");
            System.out.println("3. Cancel a Seat");
            System.out.println("4. Count Booked Seats");
            System.out.println("5. Count Empty Seats");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> printer.printAvailableSeats(service.availableSeats());

                case 2 -> {
                    System.out.print("Enter seat number: ");
                    int seat = sc.nextInt();
                    if (!service.isSeatValid(seat)) {
                        System.err.println("Invalid seat number!");
                    } else if (service.bookSeat(seat)) {
                        System.out.println("Seat " + seat + " booked successfully.");
                    } else {
                        System.err.println("Seat already booked.");
                    }
                }

                case 3 -> {
                    System.out.print("Enter seat number to cancel: ");
                    int seat = sc.nextInt();
                    if (!service.isSeatValid(seat)) {
                        System.err.println("Invalid seat number!");
                    } else if (service.cancelSeat(seat)) {
                        System.out.println("Seat " + seat + " cancelled.");
                    } else {
                        System.err.println("Seat was not booked.");
                    }
                }

                case 4 -> printer.printBookedCount(service.bookedSeats());

                case 5 -> printer.printEmptyCount(service.emptySeats());

                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.err.println("Invalid choice.");
            }
        }
    }
}
