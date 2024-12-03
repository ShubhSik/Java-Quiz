package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrueFalseQuestionTest {
    @Test
    void testCheckAnswer() {
        TrueFalseQuestion question = new TrueFalseQuestion("Is the sky blue?", "True");

        assertTrue(question.checkAnswer("True"));
        assertFalse(question.checkAnswer("False"));
    }
}
