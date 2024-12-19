public class Dog extends Mammals {
    public Dog(String name, int age, String color, String ownerName, String ownerPhone) {
        super(name, age, color, true, ownerName, ownerPhone);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating dog food and bones.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping mostly at night.");
    }

    public void bark() {
        System.out.println(getName() + " is barking loudly!");
    }

    @Override
    public Dog clone() {
        return (Dog) super.clone();
    }
}
