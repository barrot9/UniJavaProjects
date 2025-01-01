import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	GenericSet<Integer> set1 = new GenericSet<>();
        GenericSet<Integer> set2 = new GenericSet<>();
        GenericSet<Integer> set3 = new GenericSet<>();

        set1.fillWithRandomNumbers();
        set2.fillWithRandomNumbers();
        set3.fillWithRandomNumbers();

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);
        System.out.println("Set 3: " + set3);
        
        set1.union(set2);
        System.out.println("Union of Set 1 and Set 2: " + set1);

        set1.intersect(set2);
        System.out.println("Intersection of Set 1 and Set 2: " + set1);
        
     // Get two numbers from the user and create a new set
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter the first number to add to your set:");
        int num1 = scanner1.nextInt();

        System.out.println("Enter the second number to add to your set:");
        int num2 = scanner1.nextInt();
            
           
        

        GenericSet<Integer> userSet = new GenericSet<>();
        userSet.insert(num1);
        userSet.insert(num2);
        
        if(userSet.isSubset(set1) || userSet.isSubset(set2) || userSet.isSubset(set3)) {
        	System.out.println("The Set you created is a subSet of atleast one of the programmed Sets");
        }
        else {
        	System.out.println("The Set you created is not a subSet of any of the programmed Sets");
        }
        
        System.out.println("Enter a number to check if its a member of the first set:");
        int num3 = scanner1.nextInt();
        
        GenericSet<Integer> checkInSet = new GenericSet<>();
        checkInSet.insert(num3);
        
        if(set1.isSubset(checkInSet)) {
        	System.out.println("The number you entered is a member of the first set.");
        }
        else {
        	System.out.println("The number you entered is not a member of the first set.");
        }
        
        System.out.println("Please enter a number to add to the second set:");
        int num4 = scanner1.nextInt();
        set2.insert(num4);
        System.out.println("The second set after the number you inserted is: " + set2);
        
        System.out.println("Please enter a number to remove from the third set:");
        int num5 = scanner1.nextInt();
        set3.delete(num5);
        System.out.println("The third set after the number you deleted is: " + set3);
        
        GenericSet<Person> personSet = new GenericSet<>();
        
        Person p1 = new Person("12345", "John", "Doe", 1990);
        Person p2 = new Person("67890", "Taylor", "Smith", 1985);
        Person p3 = new Person("54321", "Bruno", "Brown", 1992);
        Person p4 = new Person("13524", "Rick", "Nick", 1978);
        Person p5 = new Person("24613", "Bob", "Swarly", 1964);
        
        personSet.insert(p1);
        personSet.insert(p2);
        personSet.insert(p3);
        personSet.insert(p4);
        personSet.insert(p5);
        
        System.out.println("\nThe set of persons is: \n" + personSet);
        
        Person minPerson = MinimumFinder.findMin(personSet);
        System.out.println("The Person with the smallest ID: " + minPerson);
    }
}
