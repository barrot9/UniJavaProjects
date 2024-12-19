
import java.util.HashSet;
//import java.util.Random;
import java.util.Set;

public class HangManGame {
	private String wordToGuess;  // Original word
    private char[] currentWordState; // Word state with underscores
    private Set<Character> guessedLetters;
    private int incorrectGuesses;

    public static final int MAX_TRIES = 6;

    //initialize the game
    public HangManGame(String word) {
        this.wordToGuess = word.toUpperCase();
        this.currentWordState = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            currentWordState[i] = '_';
        }
        this.guessedLetters = new HashSet<>();
        this.incorrectGuesses = 0;
    }

    //check if the letter the was guessed is correct or not and updates the game accordingly
    public boolean guessLetter(char letter) {
        letter = Character.toUpperCase(letter);
        if (guessedLetters.contains(letter)) return false;

        guessedLetters.add(letter);

        if (wordToGuess.contains(String.valueOf(letter))) {
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == letter) {
                    currentWordState[i] = letter;
                }
            }
            return true;
        } else {
            incorrectGuesses++;
            return false;
        }
    }

    //check to see if the word is guessed completly
    public boolean isWordComplete() {
        return !new String(currentWordState).contains("_");
    }

    //check if there was too many guesses
    public boolean isGameOver() {
        return incorrectGuesses >= MAX_TRIES;
    }

    //returns current number of guesses
    public int getIncorrectGuesses() {
        return incorrectGuesses;
    }

    //returns the current state of the word (letters that was guessed in the right position)
    public String getCurrentWordState() {
        return new String(currentWordState);
    }

    //returns the word to guess
    public String getWordToGuess() {
        return wordToGuess;
    }

    //returns guessed letter to present
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }
}
