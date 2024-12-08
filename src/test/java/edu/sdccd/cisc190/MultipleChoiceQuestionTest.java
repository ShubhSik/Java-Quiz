package edu.sdccd.cisc190;

import edu.sdccd.cisc190.question.MultipleChoiceQuestion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code MultipleChoiceQuestion} class.
 * <p>
 * This class verifies the functionality of the {@code checkAnswer} and {@code getChoices} methods
 * for multiple-choice questions.
 * </p>
 */
class MultipleChoiceQuestionTest {

    /**
     * Tests the {@code checkAnswer} method of the {@code MultipleChoiceQuestion} class.
     * <p>
     * Validates that the method correctly identifies:
     * </p>
     */
    @Test
    void testCheckAnswer() {
        // Arrange: Set up the question with choices and a correct answer.
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        // Act & Assert: Verify the checkAnswer method correctly evaluates answers.
        assertTrue(question.checkAnswer("4"));  // Correct answer
        assertFalse(question.checkAnswer("5")); // Incorrect answer
    }

    /**
     * Tests the {@code getChoices} method of the {@code MultipleChoiceQuestion} class.
     * <p>
     * Validates that the method returns the correct array of multiple-choice options.
     * </p>
     */
    @Test
    void testGetChoices() {
        // Arrange: Set up the question with choices.
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        // Act & Assert: Verify the getChoices method returns the expected array.
        assertArrayEquals(choices, question.getChoices());
    }
}
