import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Create a pension (ArrayList of Animals)
        ArrayList<Animals> pension = new ArrayList<>();

        // Add animals of each kind to the pension
        Dog dog = new Dog("Rex", 5, "Brown", "Bob", "987-654-3210");
        pension.add(dog);

        Cat cat = new Cat("Whiskers", 3, "White", "Alice", "123-456-7890");
        pension.add(cat);

        Parrot parrot = new Parrot("Polly", 2, "Green", 0.5, "Charlie", "555-555-5555");
        pension.add(parrot);

        Penguin penguin = new Penguin("Pingu", 4, "Black and White", 0.8, "John", "222-333-4444");
        pension.add(penguin);

        Crocodile crocodile = new Crocodile("Snap", 7, "Green");
        pension.add(crocodile);

        Chameleon chameleon = new Chameleon("Cammy", 1, "Brown");
        pension.add(chameleon);

        // Display each animal's type, kind, and call its methods
        System.out.println("Animals pension:");
        for (Animals animal : pension) {
            String type = getAnimalType(animal);
            System.out.println("Type: " + type + ", Kind: " + animal.getClass().getSimpleName());
            animal.displayInfo();
            animal.eat();  // Call the eat method
            animal.sleep();  // Call the sleep method
            callUniqueMethod(animal);  // Call the unique method
            System.out.println();
        }

        // Clone a dog and present the details of both dogs
        Dog clonedDog = dog.clone();
        System.out.println("Original Dog:");
        dog.displayInfo();
        dog.eat();
        dog.sleep();
        dog.bark();
        System.out.println();

        System.out.println("Cloned Dog:");
        clonedDog.displayInfo();
        clonedDog.eat();
        clonedDog.sleep();
        clonedDog.bark();
        System.out.println();

        // Change the owner of the cloned dog
        clonedDog.setOwnerName("David");
        clonedDog.setOwnerPhone("444-555-6666");

        // Verify the original dog remains unaffected
        System.out.println("After Modifying the Cloned Dog:");
        System.out.println("Original Dog:");
        dog.displayInfo();
        System.out.println();

        System.out.println("Cloned Dog:");
        clonedDog.displayInfo();
    }

    // Method to determine the type of the animal (Mammals, Avian, Reptiles)
    private static String getAnimalType(Animals animal) {
        if (animal instanceof Mammals) {
            return "Mammal";
        } else if (animal instanceof Avian) {
            return "Avian";
        } else if (animal instanceof Reptiles) {
            return "Reptile";
        } else {
            return "Unknown";
        }
    }

    // Method to call the unique method of each animal
    private static void callUniqueMethod(Animals animal) {
        if (animal instanceof Dog) {        	
            ((Dog) animal).bark();
            ((Mammals) animal).pregnancy();
        } else if (animal instanceof Cat) {        	
            ((Cat) animal).purr();
            ((Mammals) animal).pregnancy();
        } else if (animal instanceof Parrot) {       	
            ((Parrot) animal).mimic();
            ((Avian) animal).fly();
        } else if (animal instanceof Penguin) {       	
            ((Penguin) animal).swim();
            ((Avian) animal).fly();
        } else if (animal instanceof Crocodile) {        	
            ((Crocodile) animal).baskInSun();
            ((Reptiles) animal).skin();
        } else if (animal instanceof Chameleon) {       	
            ((Chameleon) animal).changeColor();
            ((Reptiles) animal).skin();
        }
    }
}
