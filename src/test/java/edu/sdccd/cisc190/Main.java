package edu.sdccd.cisc190;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Main {
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
