// Abstract Class Shape
abstract class Shape {
    // Abstract method to display the number of sides
    abstract void numberOfSides();

    // Method to calculate and display the sum of interior angles: (n - 2) * 180 degrees
    void sumOfAngles(int sides) {
        int sum = (sides - 2) * 180;
        System.out.println("Sum of interior angles: " + sum + " degrees");
    }
}

// Rectangle class extending Shape
class Rectangle extends Shape {
    private final int sides = 4;

    @Override
    void numberOfSides() {
        System.out.println("Shape: Rectangle");
        System.out.println("Number of sides: " + sides);
        sumOfAngles(sides);
        System.out.println("------------------------------------");
    }
}

// Triangle class extending Shape
class Triangle extends Shape {
    private final int sides = 3;

    @Override
    void numberOfSides() {
        System.out.println("Shape: Triangle");
        System.out.println("Number of sides: " + sides);
        sumOfAngles(sides);
        System.out.println("------------------------------------");
    }
}

// Hexagon class extending Shape
class Hexagon extends Shape {
    private final int sides = 6;

    @Override
    void numberOfSides() {
        System.out.println("Shape: Hexagon");
        System.out.println("Number of sides: " + sides);
        sumOfAngles(sides);
        System.out.println("------------------------------------");
    }
}

// Main class to demonstrate polymorphism
public class ShapeDemo {
    public static void main(String[] args) {
        // Polymorphic references using the abstract class Shape
        Shape shape;

        shape = new Triangle();
        shape.numberOfSides();

        shape = new Rectangle();
        shape.numberOfSides();

        shape = new Hexagon();
        shape.numberOfSides();
    }
}
