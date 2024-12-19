import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuestionsPool {
    private final List<Question> questions = new ArrayList<>();
    private int currentIndex = 0;
    
    //loading the questions from the file
    protected void loadQuestionsFromFile(String fileName) throws IOException {
        questions.clear();
        try (Scanner input = new Scanner(new File(fileName))) {
            while (input.hasNext()) {
                String questionText = input.nextLine();
                String correctAnswer = input.nextLine();
                List<String> answers = new ArrayList<>();
                answers.add(correctAnswer);
                for (int i = 0; i < 3; i++) {
                    if (input.hasNext()) {
                        answers.add(input.nextLine());
                    }
                }
                Collections.shuffle(answers); // Shuffle answers
                questions.add(new Question(questionText, correctAnswer, answers));
            }
        }
        Collections.shuffle(questions); // Shuffle question order
    }

    //loads next question
    protected Question getNextQuestion() {
        if (currentIndex < questions.size()) {
            return questions.get(currentIndex++);
        }
        return null; // No more questions
    }

    //checks if there are any questions remaining in the pool
    protected boolean hasMoreQuestions() {
        return currentIndex < questions.size();
    }
    
    //sets a new value to currentIndex (used to initialize for a new game)
    protected void setCurrentIndex(int newValue) {
    	currentIndex = newValue;
    }
}
