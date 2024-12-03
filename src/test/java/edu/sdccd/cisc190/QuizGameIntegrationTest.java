package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import org.junit.jupiter.api.Test;
//Import: Allows marking methods with @Test for testing.
import static org.junit.jupiter.api.Assertions.*;
//Assertions: Provides methods like assertTrue, assertEquals, and fail to verify test results.
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
/*Loads questions into the game and ensures there are two.
Verifies the game starts and updates the score correctly.
Ensures the game stops when ended.
 */
}
//Tests the full flow of the QuizGame, including loading questions, starting/stopping the game, and scoring.
