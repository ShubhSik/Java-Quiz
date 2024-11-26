package edu.sdccd.cisc190;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuizGame extends Game {
    private List<Question> questions;
    private int score;
    private final String highScoresFile = "src/main/resources/highscores.txt";

    public QuizGame(String gameName) {
        super(gameName);
        this.questions = new ArrayList<>();
        this.score = 0;
    }

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

    public void saveHighScore(int score) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(highScoresFile, true));
        writer.write("Score: " + score + "\n");
        writer.close();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    @Override
    public void play() {
        System.out.println("Playing QuizGame!");
    }
}
