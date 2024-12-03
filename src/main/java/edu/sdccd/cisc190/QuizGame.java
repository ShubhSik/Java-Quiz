package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/* Imports:
File handling (reading/writing files).
ArrayList for managing dynamic lists.
List interface for ordered collections.
*/
public class QuizGame extends Game {
    private final List<Question> questions;
    private int score;

/*Manages:
A list of questions for the quiz.
The player's current score.
*/
    public QuizGame(String gameName) {
        super(gameName);
        this.questions = new ArrayList<>();
        this.score = 0;
    }
/* Constructor:
Calls the parent Game class to set the game name.
Initializes an empty list of questions and sets score to 0.
*/
    public void loadQuestions(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts[0].equalsIgnoreCase("MC")) {
                questions.add(new MultipleChoiceQuestion(parts[1], parts[2], parts[3].split(",")));
            } else if (parts[0].equalsIgnoreCase("TF")) {
                questions.add(new TrueFalseQuestion(parts[1], parts[2]));
            }
        }
        reader.close();
    }
/*Loads questions from a file:
Reads each line, splits it into parts, and determines the type (MC or TF).
Adds the corresponding question to the list.
Closes the file after reading.
*/

    public void saveHighScore(int score) throws IOException {
        String highScoresFile = "src/main/resources/highscores.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresFile, true));
        writer.write("Score: " + score + "\n");
        writer.close();
    }
/* Saves the player's score to a file:
Appends the score to the high scores file.
Writes in the format "Score: <number>".
Closes the file.
*/
    public List<Question> getQuestions() {
        return questions;
    }
    // Returns the list of quiz questions
    public int getScore() {
        return score;
    }
    // Returns the player's current score.
    public void incrementScore() {
        score++;
    }
    // Increases the player's score by 1.
}
//Purpose: The QuizGame class manages a quiz game by handling questions, scoring, and saving high scores.
