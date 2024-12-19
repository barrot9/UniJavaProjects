import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class TriviaGameController {

    @FXML
    private Button EndButton;

    @FXML
    private CheckBox FirstAnswer;

    @FXML
    private CheckBox ForthAnswer;
    
    @FXML
    private Label Indicator;

    @FXML
    private Button NewGameButton;

    @FXML
    private Label PointsLabel;

    @FXML
    private Label QuestionLabel;

    @FXML
    private CheckBox SecondAnswer;

    @FXML
    private CheckBox ThirdAnswer;

    private final QuestionsPool questionsPool = new QuestionsPool();
    private Question currentQuestion;
    private int points = 0;

    @FXML
    void initialize() {
        // Initialize the game and load the first question when the program starts
        points = 0;
        PointsLabel.setText("Points: " + points);
        try {
            questionsPool.loadQuestionsFromFile("trivia.txt");
            if (questionsPool.hasMoreQuestions()) {
                showNextQuestion();
            } else {
                QuestionLabel.setText("No questions available.");
            }
        } catch (IOException e) {
            QuestionLabel.setText("Error loading questions: " + e.getMessage());
        }
    }

    @FXML
    void EndButtonPressed(ActionEvent event) {
        endGame();
    }

    @FXML
    void FirstSelected(ActionEvent event) {
        checkAnswer(0);
    }

    @FXML
    void ForthSelected(ActionEvent event) {
        checkAnswer(3);
    }

    @FXML
    void NewGamePressed(ActionEvent event) {
        startNewGame();
    }

    @FXML
    void SecondSelected(ActionEvent event) {
        checkAnswer(1);
    }

    @FXML
    void ThirdSelected(ActionEvent event) {
        checkAnswer(2);
    }

    //initialize a new game when the green button is pressed
    private void startNewGame() {
        points = 0;
        questionsPool.setCurrentIndex(0);
        PointsLabel.setText("Points: " + points);
        try {
            questionsPool.loadQuestionsFromFile("trivia.txt");
            if (questionsPool.hasMoreQuestions()) {
                showNextQuestion();
            } else {
                QuestionLabel.setText("No questions available.");
            }
        } catch (IOException e) {
            QuestionLabel.setText("Error loading questions: " + e.getMessage());
        }
    }
    
    //displays the next question
    private void showNextQuestion() {
    	FirstAnswer.setVisible(true);
        SecondAnswer.setVisible(true);
        ThirdAnswer.setVisible(true);
        ForthAnswer.setVisible(true);
        currentQuestion = questionsPool.getNextQuestion();
        if (currentQuestion != null) {
            QuestionLabel.setText(currentQuestion.getQuestionText());
            FirstAnswer.setText(currentQuestion.getAnswers().get(0));
            SecondAnswer.setText(currentQuestion.getAnswers().get(1));
            ThirdAnswer.setText(currentQuestion.getAnswers().get(2));
            ForthAnswer.setText(currentQuestion.getAnswers().get(3));
            clearSelections();
        } else {
            endGame();
        }
    }

    //clear all boxes for next question
    private void clearSelections() {
        FirstAnswer.setSelected(false);
        SecondAnswer.setSelected(false);
        ThirdAnswer.setSelected(false);
        ForthAnswer.setSelected(false);
    }

    //checks if the answer selected is correct and acts accordingly
    private void checkAnswer(int selectedAnswerIndex) {
        if (currentQuestion != null) {
            String selectedAnswer = currentQuestion.getAnswers().get(selectedAnswerIndex);
            if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            	Indicator.setText("That was correct!");
            	Indicator.setStyle("-fx-text-fill: green;");
                points += 10;
            } else {
            	Indicator.setText("that was incorrect.");
            	Indicator.setStyle("-fx-text-fill: red;");
                points -= 5;
            }
            PointsLabel.setText("Points: " + points); //displays current points count
            showNextQuestion();
        }
    }

    //trigers if the red button is pressed or all the questions from the pool are answered
    private void endGame() {
        QuestionLabel.setText("Game over! Your total points: " + points);
        clearSelections();
        FirstAnswer.setText("");
        SecondAnswer.setText("");
        ThirdAnswer.setText("");
        ForthAnswer.setText("");
        FirstAnswer.setVisible(false);
        SecondAnswer.setVisible(false);
        ThirdAnswer.setVisible(false);
        ForthAnswer.setVisible(false);
    }
}
