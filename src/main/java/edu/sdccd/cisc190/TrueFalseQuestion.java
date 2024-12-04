package edu.sdccd.cisc190;

/**
 * Represents a "True/False" type question in the quiz game.
 * <p>
 * This class extends the {@code Question} class and provides specific functionality
 * for handling questions with "True" or "False" answers.
 * </p>
 */
public class TrueFalseQuestion extends Question {

    /**
     * The correct answer for the question, either "True" or "False".
     */
    private final String correctAnswer;

    /**
     * Constructs a new {@code TrueFalseQuestion} with the specified question text and correct answer.
     *
     * @param questionText  the text of the question
     * @param correctAnswer the correct answer, either "True" or "False"
     */
    public TrueFalseQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    /**
     * Checks if the given answer matches the correct answer.
     * <p>
     * Compares the player's answer (case-insensitive) with the correct answer.
     * </p>
     *
     * @param answer the player's answer to check
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}
