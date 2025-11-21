package service;

import repository.SeatRepository;
import java.util.List;

public class BookingService {

    private final SeatRepository repository;

    public BookingService(SeatRepository repository) {
        this.repository = repository;
    }

    public boolean isSeatValid(int seat) {
        return seat >= 1 && seat <= repository.totalSeats();
    }

    public boolean bookSeat(int seat) {
        return repository.bookSeat(seat);
    }

    public boolean cancelSeat(int seat) {
        return repository.cancelSeat(seat);
    }

    public int bookedSeats() {
        return repository.countBookedSeats();
    }

    public int emptySeats() {
        return repository.countEmptySeats();
    }

    public List<Integer> availableSeats() {
        return repository.getAvailableSeats();
    }

    public int totalSeats() {
        return repository.totalSeats();
    }
}
