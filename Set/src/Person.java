
public class Person implements Comparable<Person> {
	private String id;
    private String firstName;
    private String lastName;
    private int yearOfBirth;

    // Constructor
    public Person(String id, String firstName, String lastName, int yearOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearOfBirth = yearOfBirth;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    // Override compareTo to compare based on ID number
    @Override
    public int compareTo(Person other) {
        return this.id.compareTo(other.id);
    }

    // Override equals to ensure consistency with compareTo
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person other = (Person) obj;
        return id.equals(other.id);
    }

    // Override hashCode to ensure consistency with equals
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // toString for displaying person details
    @Override
    public String toString() {
        return "Person{" +
                "ID='" + id + '\'' +
                ", First Name='" + firstName + '\'' +
                ", Last Name='" + lastName + '\'' +
                ", Year of Birth=" + yearOfBirth +
                '}' + "\n";
    }
}
