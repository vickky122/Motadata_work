package repository;

import java.util.ArrayList;
import java.util.List;

public class ArraySeatRepository implements SeatRepository{
    private final int[] seats;

    public ArraySeatRepository(int totalSeats) {
        this.seats = new int[totalSeats];
    }

    @Override
    public boolean isAvailable(int seatNumber) {
        return seats[seatNumber - 1] == 0;
    }

    @Override
    public boolean bookSeat(int seatNumber) {
        if (isAvailable(seatNumber)) {
            seats[seatNumber - 1] = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelSeat(int seatNumber) {
        if (!isAvailable(seatNumber)) {
            seats[seatNumber - 1] = 0;
            return true;
        }
        return false;
    }

    @Override
    public int totalSeats() {
        return seats.length;
    }

    @Override
    public int countBookedSeats() {
        int count = 0;
        for (int s : seats) if (s == 1) count++;
        return count;
    }

    @Override
    public int countEmptySeats() {
        int count = 0;
        for (int s : seats) if (s == 0) count++;
        return count;
    }

    @Override
    public List<Integer> getAvailableSeats() {
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) available.add(i + 1);
        }
        return available;
    }
}
