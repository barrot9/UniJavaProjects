import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class HangManController {

    @FXML
    private Canvas canv;

    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Label wordLabel;

    @FXML
    private Label guessedLettersLabel;

    @FXML
    private Button newGameButton;

    private GraphicsContext gc;
    private HangManGame hangmanGame;
    private WordManager wordManager;

    @FXML
    public void initialize() {
        gc = canv.getGraphicsContext2D();
        initializeComboBox();
        try {
            wordManager = new WordManager("words.txt");
            startNewGame();
        } catch (IOException e) {
            System.out.println("Error loading words file.");
        }
    }

    //inserting all the english letters to the combobox
    private void initializeComboBox() {
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            comboBox.getItems().add(String.valueOf(letter));
        }
    }

    //handles the selection of a letter from the combobox
    @FXML
    void guessLetter(ActionEvent event) {
        String selectedLetter = comboBox.getValue();
        if (selectedLetter != null) {
            boolean correct = hangmanGame.guessLetter(selectedLetter.charAt(0));
            updateUI();
            if (!correct) drawNextHangmanPart();
            if (hangmanGame.isWordComplete()) {
                wordLabel.setText("You won! The word was: " + hangmanGame.getWordToGuess());
            } else if (hangmanGame.isGameOver()) {
                wordLabel.setText("Game over! The word was: " + hangmanGame.getWordToGuess());
            }
        }
    }

    @FXML
    void startNewGame() {
        String randomWord = wordManager.getRandomWord();
        hangmanGame = new HangManGame(randomWord);
        clearCanvas();
        updateUI();
    }

    //updates the word current state
    private void updateUI() {
        wordLabel.setText(hangmanGame.getCurrentWordState());
        guessedLettersLabel.setText("Guessed Letters: " + hangmanGame.getGuessedLetters());
    }

    //draw a part of the hangman each mistake
    private void drawNextHangmanPart() {
        switch (hangmanGame.getIncorrectGuesses()) {
            case 1: gc.strokeOval(50, 50, 30, 30); break; // Head
            case 2: gc.strokeLine(65, 80, 65, 140); break; // Body
            case 3: gc.strokeLine(65, 100, 45, 120); break; // Left Arm
            case 4: gc.strokeLine(65, 100, 85, 120); break; // Right Arm
            case 5: gc.strokeLine(65, 140, 45, 180); break; // Left Leg
            case 6: gc.strokeLine(65, 140, 85, 180); break; // Right Leg
        }
    }

    //clears the canvas
    private void clearCanvas() {
        gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    }
}
