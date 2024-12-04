package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for the {@code QuizGame} class.
 * <p>
 * This class tests the full flow of the game, from loading questions to starting the game,
 * updating the score, and ending the game.
 * </p>
 */
class QuizGameIntegrationTest {

    /**
     * Tests the complete game flow including loading questions, starting the game, scoring, and ending the game.
     * <p>
     * This test verifies that:
     * </p>
     */
    @Test
    void testFullGameFlow() {
        // Arrange: Set up a new quiz game and load questions from a file.
        QuizGame quizGame = new QuizGame("Integration Test");
        try {
            quizGame.loadQuestions("src/test/resources/Questions.txt");
        } catch (Exception e) {
            fail("Failed to load questions: " + e.getMessage());
        }

        // Assert: Ensure that the questions were loaded correctly (expect 2 questions).
        assertEquals(2, quizGame.getQuestions().size());

        // Act: Start the game and assert that it is running.
        quizGame.startGame();
        assertTrue(quizGame.isRunning());

        // Act: Increment the score and verify it.
        quizGame.incrementScore();
        assertEquals(1, quizGame.getScore());

        // Act: End the game and verify that it has stopped.
        quizGame.endGame();
        assertFalse(quizGame.isRunning());
    }
}
