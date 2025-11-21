package repository;

import java.util.List;

public interface SeatRepository {
    boolean isAvailable(int seatNumber);
    boolean bookSeat(int seatNumber);
    boolean cancelSeat(int seatNumber);
    int totalSeats();
    int countBookedSeats();
    int countEmptySeats();
    List<Integer> getAvailableSeats();
}
