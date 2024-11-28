package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleChoiceQuestionTest {
    @Test
    void testCheckAnswer() {
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        assertTrue(question.checkAnswer("4"));
        assertFalse(question.checkAnswer("5"));
    }

    @Test
    void testGetChoices() {
        String[] choices = {"Choice1", "Choice2", "Choice3"};
        MultipleChoiceQuestion question = new MultipleChoiceQuestion("What is 2+2?", "4", choices);

        assertArrayEquals(choices, question.getChoices());
    }
}
