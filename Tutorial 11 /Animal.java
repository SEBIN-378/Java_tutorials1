package mod3;
abstract class Animal {
    protected int legs;
    protected Animal(int legs) {
        this.legs = legs;
    }
    public void walk() {
        System.out.println("This animal walks on " + legs + " legs.");
    }
    public abstract void eat();
}

interface Pet {
    String getName();
    void setName(String name);
    void play();
}

class Spider extends Animal {
    public Spider() {
        super(8);
    }

    public void eat() {
        System.out.println("Spider eats insects.");
    }
}

class Cat extends Animal implements Pet {
    private String name;

    public Cat(String name) {
        super(4);
        this.name = name;
    }

    public Cat() {
        this("");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void play() {
        System.out.println(name + " likes to play with a yarn ball.");
    }

    public void eat() {
        System.out.println("Cat eats fish and cat food.");
    }
}

class Fish extends Animal implements Pet {
    private String name;

    public Fish() {
        super(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void play() {
        System.out.println(name + " swims around playfully in the tank.");
    }

    public void walk() {
        System.out.println("Fish cannot walk, it swims!");
    }

    public void eat() {
        System.out.println("Fish eats flakes.");
    }
}

public class Main {
    public static void main(String[] args) {
        Fish f = new Fish();
        f.setName("Nemo");
        Cat c = new Cat("Fluffy");
        Animal s = new Spider();
        System.out.println("--- Fish ---");
        System.out.println("Name: " + f.getName());
        f.eat();
        f.walk();
        f.play();

        System.out.println("\n--- Cat ---");
        System.out.println("Name: " + c.getName());
        c.eat();
        c.walk();
        c.play();
        System.out.println("\n--- Spider ---");
        s.eat();
        s.walk();
    }
                           }
