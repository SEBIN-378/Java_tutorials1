package mod3;
import evenpackage.Even;

public class TestEven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        int num = scanner.nextInt();
        if (Even.isEven(num)) {
            System.out.println(num + " is an even number.");
        } 
        else {
            System.out.println(num + " is a odd number.");
        }
    }
}
