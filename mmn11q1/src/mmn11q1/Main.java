package mmn11q1;

import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			boolean playAgain = true;

			while (playAgain) {
			    Game game = new Game();
			    boolean guessedCorrectly = false;

			    System.out.println("Welcome to the Bulls-Eye Game!");
			    System.out.println("Try to guess the 4-digit number that contains 4 unique digits.");

			    while (!guessedCorrectly) {
			        // Display previous guesses
			        if (!game.getGuessHistory().isEmpty()) {
			            System.out.println("Previous Guesses:");
			            for (String entry : game.getGuessHistory()) {
			                System.out.println(entry);
			            }
			        }

			        System.out.print("Enter a 4-digit number: ");
			        String guess = scanner.nextLine();

			        // Validate input
			        if (!isNumeric(guess)) {
			            System.out.println("The number must contain only numbers, Please enter a valid number.");
			            continue;
			        } else if (guess.length() != 4) {
			            System.out.println("The number must be exactly 4 digits, Please enter a valid number.");
			            continue;
			        } else if (hasDuplicateDigits(guess)) {
			            System.out.println("The number must not contain duplicate digits, Please enter a valid number.");
			            continue;
			        }

			        // Check guess
			        String result = game.checkGuess(guess);
			        System.out.println(result);

			        if (game.isCorrectGuess(guess)) {
			            guessedCorrectly = true;
			            System.out.println("You did it!! You guessed the number in " + game.getGuessCount() + " attempts.");
			            System.out.println("The number was: " + game.getTargetNumber());
			        }
			    }

			    // Prompt for replay
			    while (true) {
			        System.out.print("Do you wish to play again? (yes/no): ");
			        String response = scanner.nextLine().trim().toLowerCase();

			        if (response.equals("yes")) {
			            playAgain = true;
			            break;
			        } else if (response.equals("no")) {
			            playAgain = false;
			            scanner.close();
			            System.out.println("Thank you for playing, See you next time!");
			            return;
			        } else {
			            System.out.println("Invalid response. Please type 'yes' or 'no'.");
			        }
			    }
			}
		}
    }

    // Helper method to validate that the input is numeric
    private static boolean isNumeric(String input) {
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    // Helper method to check for duplicate digits in the input
    private static boolean hasDuplicateDigits(String input) {
        Set<Character> uniqueDigits = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!uniqueDigits.add(c)) return true; // Duplicate found
        }
        return false;
    }
}
