import java.util.Scanner;

// Custom Exception for invalid reservation requests
class InvalidReservationException extends Exception {
    public InvalidReservationException(String message) {
        super(message);
    }
}

// HotelRoom class to manage reservations
class HotelRoom {
    public void reserveSlots(int slots) throws InvalidReservationException {
        if (slots <= 0) {
            throw new InvalidReservationException("Invalid reservation request: Number of slots must be greater than zero.");
        }
        System.out.println("Successfully reserved " + slots + " slot(s).");
    }
}

public class HotelManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelRoom room = new HotelRoom();

        System.out.println("Enter the number of slots to reserve:");
        int slots = sc.nextInt();

        try {
            room.reserveSlots(slots);
        } catch (InvalidReservationException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
