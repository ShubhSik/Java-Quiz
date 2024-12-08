package edu.sdccd.cisc190;

import edu.sdccd.cisc190.question.TrueFalseQuestion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the {@code TrueFalseQuestion} class.
 * <p>
 * This class tests the functionality of the {@code checkAnswer} method in the {@code TrueFalseQuestion}
 * class to ensure it correctly evaluates answers.
 * </p>
 */
class TrueFalseQuestionTest {

    /**
     * Tests the {@code checkAnswer} method of the {@code TrueFalseQuestion} class.
     * <p>
     * This test checks whether the {@code checkAnswer} method correctly identifies valid ("True")
     * and invalid ("False") answers for a {@code TrueFalseQuestion}.
     * </p>
     */
    @Test
    void testCheckAnswer() {
        // Creates a TrueFalseQuestion instance with the question "Is the sky blue?" and the correct answer "True"
        TrueFalseQuestion question = new TrueFalseQuestion("Is the sky blue?", "True");

        // Verifies that "True" is the correct answer
        assertTrue(question.checkAnswer("True"));

        // Verifies that "False" is an incorrect answer
        assertFalse(question.checkAnswer("False"));
    }
}

