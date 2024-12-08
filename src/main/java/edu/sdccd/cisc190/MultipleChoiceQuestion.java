package edu.sdccd.cisc190;

/**
 * Represents a multiple-choice question.
 * <p>
 * This class extends the {@link Question} class to provide functionality specific to
 * multiple-choice questions, such as storing choices and verifying answers.
 * </p>
 */
public class MultipleChoiceQuestion extends Question {

    /**
     * The correct answer for the question.
     */
    private final String correctAnswer;

    /**
     * An array of possible choices for the question.
     */
    private final String[] choices;

    /**
     * Constructs a new {@code MultipleChoiceQuestion} with the specified question text,
     * correct answer, and list of choices.
     *
     * @param questionText  the text of the question
     * @param correctAnswer the correct answer for the question
     * @param choices       an array of possible choices for the question
     */
    public MultipleChoiceQuestion(String questionText, String correctAnswer, String[] choices) {
        super(questionText); // Calls the constructor of the parent Question class
        this.correctAnswer = correctAnswer;
        this.choices = choices;
    }

    /**
     * Returns the list of choices for this question.
     *
     * @return an array of strings representing the possible choices
     */
    public String[] getChoices() {
        return choices;
    }

    /**
     * Checks if the provided answer matches the correct answer for this question.
     * <p>
     * The comparison is case-insensitive.
     * </p>
     *
     * @param answer the answer to verify
     * @return {@code true} if the answer is correct, {@code false} otherwise
     */
    @Override
    public boolean checkAnswer(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

//TODO: You could possibly add questions that have more than 4 multiple choices

