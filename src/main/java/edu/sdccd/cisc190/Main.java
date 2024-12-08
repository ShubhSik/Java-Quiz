package edu.sdccd.cisc190;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Collections; //add import statement for shuffling
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The Main class serves as the entry point for the Quiz Game application.
 * <p>
 * This JavaFX-based application provides an interactive quiz game with a countdown timer.
 * It initializes the user interface, manages game logic, and handles user interactions.
 * </p>
 */
public class Main extends Application {
    private QuizGame quizGame;
    private Label questionLabel;
    private Label timerLabel;
    private Label scoreLabel;
    private VBox optionsBox;
    private int currentQuestionIndex = 0;
    private static final int TIMER_DURATION = 300;
    private int timeLeft = TIMER_DURATION; // Timer duration in seconds (5 minutes)
    private int score = 0; //score tracking
    private ScheduledExecutorService timerExecutor;
    private boolean isPaused = false;  // Track if the game is paused
    private Button pauseButton;        // Button to toggle pause/resume

    /**
     * The main method that launches the JavaFX application.
     *
     * @param args command-line arguments (not used).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Sets up the initial welcome screen and displays the start button.
     *
     * @param primaryStage the primary stage for the application.
     */
    @Override
    public void start(Stage primaryStage) {
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
//TODO: You could possibly add somewhere in here sound effects for getting a right/wrong answer.
    /**
     * Transitions to the game screen, initializes the quiz game, and loads questions.
     *
     * @param primaryStage the primary stage for the application.
     */
    private void showGameScreen(Stage primaryStage) {
        quizGame = new QuizGame("Quiz Game");

        try {
            quizGame.loadQuestions("src/main/resources/questions.txt");
        } catch (Exception e) {
            showError("Error loading questions: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        //Randomize the order of questions
        Collections.shuffle(quizGame.getQuestions()); //shuffle the questions

        questionLabel = new Label();
        questionLabel.setWrapText(true);
        questionLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-alignment: center;");

        timerLabel = new Label("Time left: " + timeLeft + " seconds");
        timerLabel.setStyle("-fx-font-size: 16px;");

        scoreLabel = new Label("Score: " + score); // intialize score label
        scoreLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        optionsBox = new VBox(15);

        // Create Pause Button
        pauseButton = new Button("Pause Timer");
        pauseButton.setStyle("-fx-font-size: 16px;");
        pauseButton.setOnAction(e -> togglePause());  // Set action for pause/resume

        VBox gameLayout = new VBox(30, questionLabel, optionsBox, scoreLabel, timerLabel, pauseButton);
        gameLayout.setPrefSize(800, 800); //adjusted for larger window size
        gameLayout.setStyle("-fx-padding: 30; -fx-alignment: top-center; -fx-spacing: 20;");

        primaryStage.setScene(new Scene(gameLayout));
        primaryStage.show();

        startGame();
    }

    /**
     * Starts the quiz game by displaying the first question and initializing the timer.
     */
    public void startGame() {
        quizGame.startGame();
        showQuestion();
        startTimer();
    }
    /**
     * Displays the current question and its answer options.
     * <p>
     * Handles both multiple-choice and true/false questions.
     * Ends the game if there are no more questions.
     * </p>
     */
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
    /**
     * Handles the user's answer selection.
     * <p>
     * Validates the answer, updates the score if correct, and moves to the next question.
     * </p>
     *
     * @param answer the answer selected by the user.
     */
    private void handleAnswer(String answer) {
        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        if (question.checkAnswer(answer)) {
            quizGame.incrementScore();
            score++; //
            scoreLabel.setText("Score: " + score); // update score label after each correct answer
        }
        currentQuestionIndex++;
        showQuestion();
    }

    /**
     * Starts the countdown timer for the quiz.
     * <p>
     * Updates the timer label every second and ends the game when the timer reaches zero.
     * </p>
     */
    public void startTimer() {
        timerExecutor = Executors.newSingleThreadScheduledExecutor();
        timerExecutor.scheduleAtFixedRate(() -> {
            if (timeLeft > 0 && !isPaused) { //check if game is not paused
                timeLeft--;
                Platform.runLater(() -> timerLabel.setText("Time left: " + timeLeft + " seconds"));
            } else {
                timerExecutor.shutdown();
                Platform.runLater(this::endGame);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /** Pauses/resumes timer
     *
     */
    private void togglePause() {
        isPaused = !isPaused;  // Toggle the pause
        if (isPaused) {
            pauseButton.setText("Resume Timer");  //Change button text when paused
        } else {
            pauseButton.setText("Pause Timer");  //Change button text when resumed
        }
    }

    /**
     * Ends the quiz game and displays the final score.
     * <p>
     * Saves the high score and terminates the application.
     * </p>
     */
    public void endGame() {
        quizGame.endGame();
        timerExecutor.shutdown();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Game Over! Your Score: " + quizGame.getScore());
        alert.showAndWait();

        try {
            quizGame.saveHighScore(quizGame.getScore());
        } catch (Exception e) {
            showError("Error saving high score: " + e.getMessage());
        }

        System.exit(0);
    }

    /**
     * Displays an error message in an alert dialog.
     *
     * @param message the error message to display.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }
}