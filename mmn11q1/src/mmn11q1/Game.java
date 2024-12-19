package mmn11q1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private String targetNumber;
    private int guessCount;
    private ArrayList<String> guessHistory;

    // Constructor to start a new game with a random 4-digit number
    public Game() {
        this.targetNumber = generateRandomNumber();
        this.guessCount = 0;
        this.guessHistory = new ArrayList<>();
    }

    // Generate a random 4-digit number with unique digits
    private String generateRandomNumber() {
        Random rand = new Random();
        Set<Integer> digits = new HashSet<>();
        StringBuilder number = new StringBuilder();

        while (number.length() < 4) {
            int digit = rand.nextInt(10);
            if (digits.add(digit)) { // Only add unique digits
                number.append(digit);
            }
        }
        return number.toString();
    }

    // Process a guess, save it to history, and return the result as a message
    public String checkGuess(String guess) {
        guessCount++;
        int correctDigits = 0;
        int correctPositions = 0;

        // Count correct digits and correct positions
        for (int i = 0; i < 4; i++) {
            if (targetNumber.contains(String.valueOf(guess.charAt(i)))) {
                correctDigits++;
                if (targetNumber.charAt(i) == guess.charAt(i)) {
                    correctPositions++;
                }
            }
        }

        String result = "You found " + correctDigits + " correct digits and " + correctPositions + " in the correct position.";
        guessHistory.add("Guess: " + guess + " - " + result);
        return result;
    }
    //checks for right number
    public boolean isCorrectGuess(String guess) {
        return guess.equals(targetNumber);
    }
    //returns number of guesses so far
    public int getGuessCount() {
        return guessCount;
    }
    //returns the random generated number
    public String getTargetNumber() {
        return targetNumber;
    }
    //array list to hold previous guesses data
    public ArrayList<String> getGuessHistory() {
        return guessHistory;
    }
}
