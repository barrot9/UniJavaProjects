public class Reptiles extends Animals {
    private boolean isVenomous;

    // Constructor
    public Reptiles(String name, int age, String color, boolean isVenomous) {
        super(name, age, color);
        this.isVenomous = isVenomous;
    }
    
    public void skin() {
        System.out.println("Fun fact about " + getName() + ": Most reptiles can shed their skin.");
    }

    // Getters and Setters
    public boolean isVenomous() {
        return isVenomous;
    }

    public void setVenomous(boolean isVenomous) {
        this.isVenomous = isVenomous;
    }

    // Clone Method
    @Override
    public Reptiles clone() {
        return (Reptiles) super.clone();
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Venomous: " + (isVenomous ? "Yes" : "No"));
    }
}
