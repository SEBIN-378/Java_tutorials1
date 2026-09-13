package pack2;

public class Woman {
    private String name;
    private int age;

    public Woman(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayMe() {
        System.out.println("Woman's Name: " + name + ", Age: " + age);
    }
}
