package pack1;

public class Man {
    private String name;
    private int age;

    public Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayMe() {
        System.out.println("Man's Name: " + name + ", Age: " + age);
    }
}
