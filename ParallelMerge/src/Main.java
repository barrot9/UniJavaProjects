import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = getValidInteger(scanner, "Enter the length of the array (n): ");
        int m = getValidInteger(scanner, "Enter the number of threads (m): ");

        int[] array = ParallelMergeSorter.generateRandomArray(n);
        System.out.println("Original array: " + Arrays.toString(array));

        try {
            int[] sortedArray = ParallelMergeSorter.parallelMergeSort(array, n, m);
            System.out.println("Sorted array: " + Arrays.toString(sortedArray));
        } catch (InterruptedException e) {
            System.err.println("Error during sorting: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static int getValidInteger(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return value;
    }
}
