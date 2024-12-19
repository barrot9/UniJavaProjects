public class Avian extends Animals {
    private double wingSpan;
    private String ownerName;
    private String ownerPhone;

    // Constructor
    public Avian(String name, int age, String color, double wingSpan, String ownerName, String ownerPhone) {
        super(name, age, color);
        this.wingSpan = wingSpan;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
    }
    
    public void fly() {
        System.out.println("Fun fact about " + getName() + " Avian family usually is capable of flying");
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
    public Avian clone() {
        Avian cloned = (Avian) super.clone();
        cloned.ownerName = ownerName;  // Clone owner details
        cloned.ownerPhone = ownerPhone;
        return cloned;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Wing Span: " + wingSpan + " meters");
        if (ownerName != null && ownerPhone != null) {
            System.out.println("Owner: " + ownerName);
            System.out.println("Contact: " + ownerPhone);
        } else {
            System.out.println("No owner information available.");
        }
    }
}
