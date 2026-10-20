import java.util.Scanner;

// Interface Displayable
interface Displayable {
    void showDetails(int days);
}

// Abstract Class Vehicle
abstract class Vehicle {
    protected int vehicleId;
    protected String vehicleName;
    protected double rentalPricePerDay;

    // Parameterized constructor
    public Vehicle(int vehicleId, String vehicleName, double rentalPricePerDay) {
        this.vehicleId = vehicleId;
        this.vehicleName = vehicleName;
        this.rentalPricePerDay = rentalPricePerDay;
    }

    // Abstract method to be overridden by subclasses
    abstract double calculateRentalAmount(int days);

    // Concrete method to display base vehicle specifications
    public void displayVehicleDetails() {
        System.out.println("Vehicle ID           : " + vehicleId);
        System.out.println("Vehicle Name         : " + vehicleName);
        System.out.println("Rental Price / Day   : Rs. " + rentalPricePerDay);
    }
}

// Concrete Subclass Car
class Car extends Vehicle implements Displayable {

    public Car(int vehicleId, String vehicleName, double rentalPricePerDay) {
        super(vehicleId, vehicleName, rentalPricePerDay);
    }

    @Override
    double calculateRentalAmount(int days) {
        double total = rentalPricePerDay * days;
        // 10% discount if rented for more than 5 days
        if (days > 5) {
            total = total - (0.10 * total);
        }
        return total;
    }

    @Override
    public void showDetails(int days) {
        displayVehicleDetails();
        System.out.println("Days Rented          : " + days);
        System.out.println("Total Rental Amount  : Rs. " + calculateRentalAmount(days));
        System.out.println("-----------------------------------------");
    }
}

// Concrete Subclass Bike
class Bike extends Vehicle implements Displayable {

    public Bike(int vehicleId, String vehicleName, double rentalPricePerDay) {
        super(vehicleId, vehicleName, rentalPricePerDay);
    }

    @Override
    double calculateRentalAmount(int days) {
        double total = rentalPricePerDay * days;
        // 5% discount if rented for more than 3 days
        if (days > 3) {
            total = total - (0.05 * total);
        }
        return total;
    }

    @Override
    public void showDetails(int days) {
        displayVehicleDetails();
        System.out.println("Days Rented          : " + days);
        System.out.println("Total Rental Amount  : Rs. " + calculateRentalAmount(days));
        System.out.println("-----------------------------------------");
    }
}

// Main Class
public class VehicleRentalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Instantiate Car and Bike objects with sample base data
        Car car = new Car(101, "Honda City", 2000.0);
        Bike bike = new Bike(201, "Royal Enfield", 500.0);

        // Input rental duration
        System.out.print("Enter number of rental days for Car: ");
        int carDays = scanner.nextInt();

        System.out.print("Enter number of rental days for Bike: ");
        int bikeDays = scanner.nextInt();

        System.out.println("\n========== RENTAL SUMMARY ==========\n");

        // Display results
        car.showDetails(carDays);
        bike.showDetails(bikeDays);

        scanner.close();
    }
}
