package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import org.junit.jupiter.api.Test;
//@Test marks methods as JUnit test cases.
import static org.junit.jupiter.api.Assertions.*;
// Provides assertion methods like assertTrue, assertFalse, and assertArrayEquals for validating test results.
class MultipleChoiceQuestionTest {
    @Test
    void testCheckAnswer() {
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        assertTrue(question.checkAnswer("4"));
        assertFalse(question.checkAnswer("5"));
    }
//testCheckAnswer: Checks if the checkAnswer method correctly identifies valid ("4") and invalid ("5") answers.
    @Test
    void testGetChoices() {
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        assertArrayEquals(choices, question.getChoices());
    }
//testGetChoices: Verifies the getChoices method returns the correct array of choices.
}
//Validates the behavior of MultipleChoiceQuestion methods (checkAnswer and getChoices).
