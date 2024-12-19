public class Penguin extends Avian {
    // Constructor with owner information
    public Penguin(String name, int age, String color, double wingSpan, String ownerName, String ownerPhone) {
        super(name, age, color, wingSpan, ownerName, ownerPhone);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating fish.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping when its dark.");
    }

    public void swim() {
        System.out.println(getName() + " is swimming gracefully in the water.");
    }

    @Override
    public Penguin clone() {
        return (Penguin) super.clone();
    }
}
