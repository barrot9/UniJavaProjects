import java.util.List;

public class Question {
    private final String questionText;
    private final String correctAnswer;
    private final List<String> answers;

    //question constructor
    protected Question(String questionText, String correctAnswer, List<String> answers) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.answers = answers;
    }

    //get methods
    protected String getQuestionText() {
        return questionText;
    }

    protected String getCorrectAnswer() {
        return correctAnswer;
    }

    protected List<String> getAnswers() {
        return answers;
    }
}
	