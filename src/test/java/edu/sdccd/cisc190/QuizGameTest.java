package edu.sdccd.cisc190;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class QuizGameTest {
    private QuizGame quizGame;

    @BeforeEach
    void setup() {
        quizGame = new QuizGame("Quiz Test");
    }

    @Test
    void testLoadQuestions() throws IOException {
        quizGame.loadQuestions("src/test/resources/Questions.txt");
        assertEquals(2, quizGame.getQuestions().size());
    }

    @Test
    void testScoreIncrement() {
        assertEquals(0, quizGame.getScore());
        quizGame.incrementScore();
        assertEquals(1, quizGame.getScore());
    }

    @Test
    void testSaveHighScore() {
        assertDoesNotThrow(() -> quizGame.saveHighScore(100));
    }
}
