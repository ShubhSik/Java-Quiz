package edu.sdccd.cisc190;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizGameIntegrationTest {
    @Test
    void testFullGameFlow() {
        QuizGame quizGame = new QuizGame("Integration Test");
        try {
            quizGame.loadQuestions("src/test/resources/Questions.txt");
        } catch (Exception e) {
            fail("Failed to load questions: " + e.getMessage());
        }

        assertEquals(2, quizGame.getQuestions().size());

        quizGame.startGame();
        assertTrue(quizGame.isRunning());

        quizGame.incrementScore();
        assertEquals(1, quizGame.getScore());

        quizGame.endGame();
        assertFalse(quizGame.isRunning());
    }
}
