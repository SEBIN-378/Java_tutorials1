import java.util.Scanner;

// Custom Checked Exception
class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class PositiveNumberAverage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = 5; // Fixed to 5 to match "Enter 5 Numbers", or read 'n' from user if required
        int[] numbers = new int[n];
        int sum = 0;
        int validCount = 0;

        System.out.println("Enter " + n + " Numbers");
        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        System.out.println("Numbers are");
        for (int i = 0; i < n; i++) {
            try {
                if (numbers[i] <= 0) {
                    throw new InvalidNumberException("invalid Number" + numbers[i]);
                }
                System.out.println(numbers[i]);
                sum += numbers[i];
                validCount++;
            } catch (InvalidNumberException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nsum of the positive numbers are: " + sum);
        if (validCount > 0) {
            double average = (double) sum / validCount;
            System.out.println("Average of these numbers: " + average);
        } else {
            System.out.println("Average of these numbers: 0.0");
        }

        sc.close();
    }
}
