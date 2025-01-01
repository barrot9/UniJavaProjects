import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GenericSet<T> {
    private ArrayList<T> elements;

    // Empty constructor initializes the set as an empty set
    public GenericSet() {
        elements = new ArrayList<>();
    }

    // Constructor that receives an array and creates a set
    public GenericSet(T[] array) {
        elements = new ArrayList<>();
        for (T item : array) {
            insert(item);
        }
    }

    // Union: Unites the current set with another set
    public void union(GenericSet<T> otherSet) {
        for (T item : otherSet.elements) {
            insert(item);
        }
    }

    // Intersect: Forms the intersection of the sets
    public void intersect(GenericSet<T> otherSet) {
        elements.removeIf(item -> !otherSet.isMember(item));
    }

    // isSubset: Checks if the provided set is a subset of this set
    public boolean isSubset(GenericSet<T> otherSet) {
        for (T item : otherSet.elements) {
            if (isMember(item)) {
                return true;
            }
        }
        return false;
    }

    // isMember: Checks if an element is part of the set
    public boolean isMember(T element) {
        return elements.contains(element);
    }

    // Insert: Adds an element to the set (if not already present)
    public void insert(T element) {
        if (!isMember(element)) {
            elements.add(element);
        }
    }

    // Delete: Removes an element from the set (if present)
    public void delete(T element) {
        elements.remove(element);
    }

    // Iterator: Returns an iterator to go over the set's elements
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    // toString: Returns a string representation of the set
    @Override
    public String toString() {
        return elements.toString();
    }
    
 // Method to fill the set with 10 random numbers between 1 and 100
    public void fillWithRandomNumbers() {
        Random random = new Random();
        while (elements.size() < 10) {
            Integer randomNumber = random.nextInt(100) + 1; // Generate random number between 1 and 100
            if (!isMember((T) randomNumber)) { // Check if the number is already in the set
                insert((T) randomNumber);
            }
        }
    }
}
