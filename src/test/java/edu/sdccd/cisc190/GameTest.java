package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import org.junit.jupiter.api.Test;
//Allows marking methods as test cases in JUnit 5.
import static org.junit.jupiter.api.Assertions.*;
//Provides assertion methods like assertTrue and assertFalse to validate test conditions.
class GameTest {
    @Test
    void testStartAndEndGame() {
        Game game = new QuizGame("Test Quiz");
        assertFalse(game.isRunning());//Confirms game isn't running initially.

        game.startGame();
        assertTrue(game.isRunning());// Verifies the game starts properly.

        game.endGame();
        assertFalse(game.isRunning());// Ensures the game stops correctly.
    }
/*Checks the game starts and stops as expected.
Uses assertions to confirm the game's running state.
 */
}
//Purpose: Tests if startGame and endGame work properly.
