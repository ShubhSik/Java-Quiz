package edu.sdccd.cisc190;

/**
 * Represents a general question in a quiz or game.
 * <p>
 * This is an abstract base class that provides the common functionality of storing
 * the question text and defines an abstract method for answer validation.
 * </p>
 */
public abstract class Question {

    /**
     * The text of the question.
     */
    private final String questionText;

    /**
     * Constructs a new {@code Question} with the specified question text.
     *
     * @param questionText the text of the question
     */
    public Question(String questionText) {
        this.questionText = questionText;
    }

    /**
     * Returns the text of the question.
     *
     * @return the question text
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Checks if the provided answer is correct.
     * <p>
     * This method must be implemented by subclasses to define specific answer-checking logic.
     * </p>
     *
     * @param answer the answer to validate
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    public abstract boolean checkAnswer(String answer);
}
//TODO: a possible implementation that you could add is changing and varying the difficulty levels for the questions such as easy, medium, or hard.