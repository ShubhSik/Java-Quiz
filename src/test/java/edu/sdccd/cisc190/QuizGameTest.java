package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code QuizGame} class.
 * <p>
 * This class tests various methods of the {@code QuizGame} class, including loading questions,
 * incrementing the score, and saving the high score.
 * </p>
 */
class QuizGameTest {
    private QuizGame quizGame;

    /**
     * Sets up a new {@code QuizGame} instance before each test.
     * <p>
     * This method is executed before each test method to initialize the {@code quizGame} object,
     * ensuring a fresh instance for every test.
     * </p>
     */
    @BeforeEach
    void setup() {
        quizGame = new QuizGame("Quiz Test");
    }

    /**
     * Tests the loading of questions from a file.
     * <p>
     * This test verifies that two questions are loaded correctly from the specified file.
     * It checks that the size of the loaded question list is 2.
     * </p>
     */
    @Test
    void testLoadQuestions() throws IOException {
        quizGame.loadQuestions("src/test/resources/Questions.txt");
        assertEquals(2, quizGame.getQuestions().size());
    }

    /**
     * Tests the incrementing of the game score.
     * <p>
     * This test checks that the score starts at 0 and increments by 1 after calling the
     * </p>
     */
    @Test
    void testScoreIncrement() {
        assertEquals(0, quizGame.getScore());  // Ensure initial score is 0
        quizGame.incrementScore();  // Increment score
        assertEquals(1, quizGame.getScore());  // Verify score is incremented to 1
    }

    /**
     * Tests the saving of the high score.
     * <p>
     * This test checks that no exceptions are thrown when attempting to save a high score
     * using the {@code saveHighScore} method.
     * </p>
     */
    @Test
    void testSaveHighScore() {
        // Verify that saving a high score of 100 does not throw any exceptions
        assertDoesNotThrow(() -> quizGame.saveHighScore(100));
    }
}
