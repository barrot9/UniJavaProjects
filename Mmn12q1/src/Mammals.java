public class Mammals extends Animals {
    private boolean isDomesticated;
    private String ownerName;
    private String ownerPhone;

    // Constructor
    public Mammals(String name, int age, String color, boolean isDomesticated, String ownerName, String ownerPhone) {
        super(name, age, color);
        this.isDomesticated = isDomesticated;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }
    
    public void pregnancy() {
        System.out.println("Fun fact about " + getName() + ": Mammals family can get pregnant.");
    }

    // Getters and Setters for Owner
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    // Clone Method
    @Override
    public Mammals clone() {
        Mammals cloned = (Mammals) super.clone();
        cloned.ownerName = ownerName;  // Clone owner details
        cloned.ownerPhone = ownerPhone;
        return cloned;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Domesticated: " + (isDomesticated ? "Yes" : "No"));
        if (ownerName != null && ownerPhone != null) {
            System.out.println("Owner: " + ownerName);
            System.out.println("Contact: " + ownerPhone);
        } else {
            System.out.println("No owner information available.");
        }
    }
}
