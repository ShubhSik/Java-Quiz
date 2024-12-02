package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/*Imports classes for file handling and input/output operations (e.g., reading/writing files, handling exceptions).
Imports ArrayList, a resizable array for managing lists of data dynamically.
Imports the List interface, which ArrayList implements, for working with ordered collections flexibly.
 */
public class QuizGame extends Game {
    private final List<Question> questions;
    private int score;

    /*This defines the QuizGame class, which is a child of the Game class.
It introduces three variables:
questions stores a list of all quiz questions.
score keeps track of the player’s current score.
highScoresFile is the file where high scores will be saved, and its value cannot be changed.
*/
    public QuizGame(String gameName) {
        super(gameName);
        this.questions = new ArrayList<>();
        this.score = 0;
    }
/* This is the constructor for the QuizGame class.
It calls the parent Game class’s constructor to set the game’s name.
It initializes an empty list of questions and sets the player’s starting score to 0.
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
/* This method reads questions from a file and adds them to the questions list.
Each line in the file represents a question, which is split into parts using ; as the separator.
If the line specifies a multiple-choice question (MC), it creates a MultipleChoiceQuestion object.
If the line specifies a true/false question (TF), it creates a TrueFalseQuestion object.
The method closes the file after reading all the questions.
*/
    public void saveHighScore(int score) throws IOException {
        String highScoresFile = "src/main/resources/highscores.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresFile, true));
        writer.write("Score: " + score + "\n");
        writer.close();
    }
/*This method saves the player’s score to the high scores file.
It opens the file in "append" mode, so new scores are added without erasing existing ones.
It writes the score in the format Score: <number> and closes the file afterward.
*/
    public List<Question> getQuestions() {
        return questions;
    }
//getQuestions(): Returns the list of quiz questions.
    public int getScore() {
        return score;
    }
//getScore(): Returns the player’s current score.
    public void incrementScore() {
        score++;
    }

}
/*Purpose: The QuizGame class manages a quiz game by handling questions, scoring, and saving high scores.
Key Features:
Stores Questions: Uses a list to hold different types of questions (e.g., multiple-choice, true/false).
Tracks Score: Keeps the player's score, starting at 0, and provides a method to increase it.
Loads Questions: Reads questions from a file and adds them to the game.
Saves High Scores: Appends the player's score to a file for tracking high scores.
Game Logic: Provides a placeholder play method for running the game.
Parent Class: It extends the Game class, inheriting its properties and methods while adding custom functionality for quizzes.
*/