public class Cat extends Mammals {
    public Cat(String name, int age, String color, String ownerName, String ownerPhone) {
        super(name, age, color, true, ownerName, ownerPhone);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is eating cat food and sometimes hunting small prey.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping 16 hours a day.");
    }

    public void purr() {
        System.out.println(getName() + " is purring happily.");
    }

    @Override
    public Cat clone() {
        return (Cat) super.clone();
    }
}
