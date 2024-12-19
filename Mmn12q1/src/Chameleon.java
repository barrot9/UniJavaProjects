public class Chameleon extends Reptiles {
    public Chameleon(String name, int age, String color) {
        super(name, age, color, false);
    }

    @Override
    public void eat() {
        System.out.println(getName() + " is catching insects with its long tongue.");
    }
    
    public void sleep() {
        System.out.println(getName() + " is sleeping when it feels safe.");
    }

    public void changeColor() {
        System.out.println(getName() + " is changing its color to blend into the surroundings.");
    }

    @Override
    public Chameleon clone() {
        return (Chameleon) super.clone();
    }
}
