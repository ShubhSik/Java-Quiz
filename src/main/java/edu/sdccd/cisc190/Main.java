package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends Application {
    private QuizGame quizGame;
    private Label questionLabel;
    private Label timerLabel;
    private VBox optionsBox;
    private int currentQuestionIndex = 0;
    private int timeLeft = 30; // 30 seconds timer
    private ScheduledExecutorService timerExecutor;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        quizGame = new QuizGame("Quiz Game");

        try {
            quizGame.loadQuestions("src/main/resources/questions.txt");
        } catch (Exception e) {
            showError("Error loading questions: " + e.getMessage());
            return;
        }

        primaryStage.setTitle("Quiz Game");

        questionLabel = new Label();
        timerLabel = new Label("Time left: " + timeLeft + " seconds");
        optionsBox = new VBox(10);

        VBox root = new VBox(20, questionLabel, optionsBox, timerLabel);
        root.setPrefSize(400, 300);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        startGame();
    }

    private void startGame() {
        quizGame.startGame();
        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        if (currentQuestionIndex >= quizGame.getQuestions().size()) {
            endGame();
            return;
        }

        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        questionLabel.setText(question.getQuestionText());
        optionsBox.getChildren().clear();

        if (question instanceof MultipleChoiceQuestion mcQuestion) {
            for (String choice : mcQuestion.getChoices()) {
                Button choiceButton = new Button(choice);
                choiceButton.setOnAction(e -> handleAnswer(choice));
                optionsBox.getChildren().add(choiceButton);
            }
        } else if (question instanceof TrueFalseQuestion) {
            Button trueButton = new Button("True");
            trueButton.setOnAction(e -> handleAnswer("True"));
            Button falseButton = new Button("False");
            falseButton.setOnAction(e -> handleAnswer("False"));

            optionsBox.getChildren().addAll(trueButton, falseButton);
        }
    }

    private void handleAnswer(String answer) {
        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        if (question.checkAnswer(answer)) {
            quizGame.incrementScore();
        }
        currentQuestionIndex++;
        showQuestion();
    }

    private void startTimer() {
        timerExecutor = Executors.newSingleThreadScheduledExecutor();
        timerExecutor.scheduleAtFixedRate(() -> {
            if (timeLeft > 0) {
                timeLeft--;
                // Use Platform.runLater to ensure timer updates are done on the JavaFX application thread
                Platform.runLater(() -> timerLabel.setText("Time left: " + timeLeft + " seconds"));
            } else {
                timerExecutor.shutdown();
                Platform.runLater(this::endGame);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void endGame() {
        quizGame.endGame();
        timerExecutor.shutdown();
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Game Over! Your Score: " + quizGame.getScore());
        alert.showAndWait();
        try {
            quizGame.saveHighScore(quizGame.getScore());
        } catch (Exception e) {
            showError("Error saving high score: " + e.getMessage());
        }
        System.exit(0);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}
