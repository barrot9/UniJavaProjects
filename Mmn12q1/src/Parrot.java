public class Parrot extends Avian {
    public Parrot(String name, int age, String color, double wingSpan, String ownerName, String ownerPhone) {
        super(name, age, color, wingSpan, ownerName, ownerPhone);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating fruits and nuts.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping when its dark.");
    }

    public void mimic() {
        System.out.println(getName() + " is mimicking sounds and words.");
    }

    @Override
    public Parrot clone() {
        return (Parrot) super.clone();
    }
}
