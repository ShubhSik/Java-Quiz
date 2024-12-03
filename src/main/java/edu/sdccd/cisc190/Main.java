package edu.sdccd.cisc190;
//Specifies the folder (package) where this class belongs.
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
// Imports tools for JavaFX UI and background timer functionality.
public class Main extends Application {
    private QuizGame quizGame;
    private Label questionLabel;
    private Label timerLabel;
    private VBox optionsBox;
    private int currentQuestionIndex = 0;
    private int timeLeft = 300; // 60 seconds timer
    private ScheduledExecutorService timerExecutor;
    // Variables for managing the game, timer, and UI elements.
    public static void main(String[] args) {
        launch(args);
    }
    // Launches the JavaFX app.

    @Override
    public void start(Stage primaryStage) {
        // Displays a welcome screen with a start button.
        Label banner = new Label("Welcome to the Quiz Game!");
        banner.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button startButton = new Button("Start Game");
        startButton.setStyle("-fx-font-size: 18px;");
        startButton.setOnAction(e -> showGameScreen(primaryStage));

        VBox welcomeLayout = new VBox(20, banner, startButton);
        welcomeLayout.setPrefSize(400, 300);
        welcomeLayout.setStyle("-fx-alignment: center;");

        primaryStage.setTitle("Quiz Game");
        primaryStage.setScene(new Scene(welcomeLayout));
        primaryStage.show();
    }
    // Sets up the game screen with questions, options, and a timer.
    private void showGameScreen(Stage primaryStage) {
        quizGame = new QuizGame("Quiz Game");

        try {
            quizGame.loadQuestions("src/main/resources/questions.txt");
        } catch (Exception e) {
            showError("Error loading questions: " + e.getMessage());
            return;
        }
        questionLabel = new Label();
        questionLabel.setWrapText(true); // Enables text wrapping
        questionLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");

        timerLabel = new Label("Time left: " + timeLeft + " seconds");
        timerLabel.setStyle("-fx-font-size: 16px;");
        optionsBox = new VBox(15);

        VBox gameLayout = new VBox(30, questionLabel, optionsBox, timerLabel);
        gameLayout.setPrefSize(600, 400); // Increased page size
        gameLayout.setStyle("-fx-padding: 30; -fx-alignment: top-center; -fx-spacing: 20;");

        primaryStage.setScene(new Scene(gameLayout));
       // primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();

        startGame();
    }

    public void startGame() {
        // Starts the game and displays the first question.
        quizGame.startGame();
        showQuestion();
        startTimer();
    }

    private void showQuestion() {
        if (currentQuestionIndex >= quizGame.getQuestions().size()) {
            endGame();
            return; // Ends the game if no more questions are left.
        }
        // Shows the current question and answer options.
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
        // Checks if the answer is correct, updates score, and shows the next question.
        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        if (question.checkAnswer(answer)) {
            quizGame.incrementScore();
        }
        currentQuestionIndex++;
        showQuestion();
    }

    public void startTimer() {
        // Starts a countdown timer that updates the label every second.
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

    public void endGame() {
        // Stops the game, shows the final score, and saves it.
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
        // Shows an error message in a dialog box.
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}
//Purpose: The Main class is the entry point for the quiz game. It sets up the user interface, handles game logic, and manages a timer using JavaFX.
