public class Crocodile extends Reptiles {
    public Crocodile(String name, int age, String color) {
        super(name, age, color, false);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating fish and other animals near the riverbank.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping mostly at day time.");
    }

    public void baskInSun() {
        System.out.println(getName() + " is basking in the sun to warm up.");
    }

    @Override
    public Crocodile clone() {
        return (Crocodile) super.clone();
    }
}
