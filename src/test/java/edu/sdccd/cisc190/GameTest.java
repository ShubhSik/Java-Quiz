package edu.sdccd.cisc190;

import edu.sdccd.cisc190.game.Game;
import edu.sdccd.cisc190.game.QuizGame;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code Game} class.
 * <p>
 * This class verifies the functionality of starting and ending a game using JUnit 5.
 * </p>
 */
class GameTest {

    /**
     * Tests the {@code startGame} and {@code endGame} methods of the {@code Game} class.
     * <p>
     * Validates the following:
     * </p>
     */
    @Test
    void testStartAndEndGame() {
        // Arrange: Create a new QuizGame instance.
        Game game = new QuizGame("Test Quiz");

        // Assert: Verify that the game is not running initially.
        assertFalse(game.isRunning());

        // Act: Start the game.
        game.startGame();

        // Assert: Verify that the game is running after starting.
        assertTrue(game.isRunning());

        // Act: End the game.
        game.endGame();

        // Assert: Verify that the game is no longer running after stopping.
        assertFalse(game.isRunning());
    }
}
