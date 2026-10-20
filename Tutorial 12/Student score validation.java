import java.util.Scanner;

// Custom Unchecked Exception (extends RuntimeException)
class InvalidScoreException extends RuntimeException {
    public InvalidScoreException(String message) {
        super(message);
    }
}

class Student {
    private int score;

    public void checkScore(int score) {
        if (score < 0 || score > 100) {
            throw new InvalidScoreException("Score must be between 0 and 100.");
        }
        this.score = score;
        System.out.println("The score of the student" + this.score);
    }
}

public class StudentScoreDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student student = new Student();

        System.out.println("enter the score of the student");
        int inputScore = sc.nextInt();

        try {
            student.checkScore(inputScore);
        } catch (InvalidScoreException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
