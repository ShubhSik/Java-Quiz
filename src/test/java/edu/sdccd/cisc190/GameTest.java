package edu.sdccd.cisc190;

import javafx.scene.Node;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;

class GameTest {
    @Test
    void testStartAndEndGame() {
        Game game = new QuizGame("Test Quiz");
        assertFalse(game.isRunning());

        game.startGame();
        assertTrue(game.isRunning());

        game.endGame();
        assertFalse(game.isRunning());
    }
}
