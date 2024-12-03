package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*@BeforeEach: Sets up test-specific data before each test.
@Test: Marks methods as test cases
*/
import java.io.IOException;
//Import: Allows handling file-related errors, like reading or writing files.
import static org.junit.jupiter.api.Assertions.*;
//Assertions: Provides methods like assertEquals and assertDoesNotThrow to check conditions.
class QuizGameTest {
    private QuizGame quizGame;

    @BeforeEach
    void setup() {
        quizGame = new QuizGame("Quiz Test");
    }
//setup: Prepares a new QuizGame instance for each test.
    @Test
    void testLoadQuestions() throws IOException {
        quizGame.loadQuestions("src/test/resources/Questions.txt");
        assertEquals(2, quizGame.getQuestions().size());
    }
//testLoadQuestions: Verifies the game loads two questions from the file correctly.
    @Test
    void testScoreIncrement() {
        assertEquals(0, quizGame.getScore());
        quizGame.incrementScore();
        assertEquals(1, quizGame.getScore());
    }
//testScoreIncrement: Checks the score starts at 0 and increments properly.
    @Test
    void testSaveHighScore() {
        assertDoesNotThrow(() -> quizGame.saveHighScore(100));
    }
//testScoreIncrement: Checks the score starts at 0 and increments properly.
}
//Tests key QuizGame methods, including loading questions, scoring, and saving high scores.
