package edu.sdccd.cisc190;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a quiz game that manages questions, scoring, and high scores.
 * <p>
 * This class extends the {@code Game} class and provides specific functionality
 * for managing quiz questions and scores, loading questions from a file, and saving high scores.
 * </p>
 */
public class QuizGame extends Game {

    /**
     * A list of questions for the quiz game.
     */
    private final List<Question> questions;

    /**
     * The player's current score in the game.
     */
    private int score;

    /**
     * Constructs a new {@code QuizGame} with the specified game name.
     * <p>
     * Initializes an empty list of questions and sets the initial score to 0.
     * </p>
     *
     * @param gameName the name of the game
     */
    public QuizGame(String gameName) {
        super(gameName);
        this.questions = new ArrayList<>();
        this.score = 0;
    }

    /**
     * Loads questions from a specified file.
     * <p>
     * The file should contain questions in a specific format where each line represents a question:
     * For multiple-choice questions (MC): "MC;questionText;correctAnswer;choice1,choice2,choice3"
     * For true/false questions (TF): "TF;questionText;correctAnswer"
     * </p>
     *
     * @param filePath the path to the file containing questions
     * @throws IOException if an I/O error occurs while reading the file
     */
    public void loadQuestions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            switch (parts[0].toUpperCase()) {
                case "MC":
                    questions.add(new MultipleChoiceQuestion(parts[1], parts[2], parts[3].split(",")));
                    break;
                case "TF":
                    questions.add(new TrueFalseQuestion(parts[1], parts[2]));
                    break;
                default:
                    System.out.println("Unknown question type: " + parts[0]);
            } // Replaced if-else with a switch which improves readability and scalable for handling multiple cases. For example, if a new case is introduced such as short answers
        }
        reader.close();
    }

    /**
     * Saves the player's high score to a file.
     * <p>
     * The score is appended to a file named "highscores.txt" in the format:
     * "Score: &lt;number&gt;"
     * </p>
     *
     * @param score the player's score to save
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveHighScore(int score) throws IOException {
        String highScoresFile = "src/main/resources/highscores.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresFile, true));
        writer.write("Score: " + score + "\n");
        writer.close();
    }

    /**
     * Returns the list of questions in the game.
     *
     * @return a list of {@code Question} objects
     */
    public List<Question> getQuestions() {
        return questions;
    }

    /**
     * Returns the player's current score.
     *
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Increments the player's score by 1.
     */
    public void incrementScore() {
        score++;
    }
}