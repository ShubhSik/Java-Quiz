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
/*These are the import statements, which bring in the necessary JavaFX and utility classes.
Application is the base class for JavaFX applications.
Platform helps with updates on the JavaFX Application Thread.
Scene, Label, Button, and VBox are JavaFX components for building the user interface.
Executors, ScheduledExecutorService, and TimeUnit are used for running a timer in the background.
 */
public class Main extends Application {
    private QuizGame quizGame;
    private Label questionLabel;
    private Label timerLabel;
    private VBox optionsBox;
    private int currentQuestionIndex = 0;
    private int timeLeft = 300; // 60 seconds timer
    private ScheduledExecutorService timerExecutor;
    /*This defines the Main class that extends Application to create a JavaFX application.
    quizGame: Holds the main game logic.
    questionLabel: Displays the current question text.
    timerLabel: Shows the countdown timer.
    optionsBox: A vertical layout to display answer options.
    currentQuestionIndex: Tracks which question is currently being displayed.
    timeLeft: Sets the countdown timer to 300 seconds (5 minutes).
    timerExecutor: Manages the timer in the background using a scheduled executor service.
    */
    public static void main(String[] args) {
        launch(args);
    }
    /*The program’s entry point.
    Calls launch, which initializes the JavaFX environment and triggers the start method.
    */
   /* @Override
    public void start(Stage primaryStage) {
        quizGame = new QuizGame("Quiz Game");

        try {
            quizGame.loadQuestions("src/main/resources/questions.txt");
        } catch (Exception e) {
            showError("Error loading questions: " + e.getMessage());
            return;
        }
/*Creates a new QuizGame object and assigns it to quizGame.
Loads questions from the file questions.txt. If an error occurs during loading, it shows an error message and exits the method.
*/
    /*      primaryStage.setTitle("Quiz Game");
     */

    @Override
    public void start(Stage primaryStage) {
        // Welcome Screen
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
/*Sets the window title to "Quiz Game."
Initializes labels and a vertical layout box (VBox) to display the question, answer options, and the timer.
 */
   /*     VBox root = new VBox(20, questionLabel, optionsBox, timerLabel);
        root.setPrefSize(400, 300);

        primaryStage.setScene(new Scene(root));
        primaryStage.show(); */

        VBox gameLayout = new VBox(30, questionLabel, optionsBox, timerLabel);
        gameLayout.setPrefSize(600, 400); // Increased page size
        gameLayout.setStyle("-fx-padding: 30; -fx-alignment: top-center; -fx-spacing: 20;");

        primaryStage.setScene(new Scene(gameLayout));
       /* primaryStage.setScene(new Scene(root, 600, 400)); */
        primaryStage.show();

        startGame();
/*Uses a VBox to arrange UI components vertically with spacing.
Creates a Scene object with this layout and attaches it to the stage (main window).
Displays the stage and calls startGame to begin the quiz.
 */
    }

    public void startGame() {
        quizGame.startGame();
        showQuestion();
        startTimer();
/*Calls the startGame method from QuizGame to initialize the game logic.
Displays the first question using showQuestion().
Starts the countdown timer using startTimer().
 */
    }

    private void showQuestion() {
        if (currentQuestionIndex >= quizGame.getQuestions().size()) {
            endGame();
            return;
//Checks if all questions have been answered. If so, it calls endGame().
        }

        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        questionLabel.setText(question.getQuestionText());
        optionsBox.getChildren().clear();
//Retrieves the current question and sets the question text on questionLabel.
        if (question instanceof MultipleChoiceQuestion mcQuestion) {
            for (String choice : mcQuestion.getChoices()) {
                Button choiceButton = new Button(choice);
                choiceButton.setOnAction(e -> handleAnswer(choice));
                optionsBox.getChildren().add(choiceButton);
/*Loops through the answer choices and creates a button for each.
Adds click event listeners to buttons that call handleAnswer() when clicked.
*/
            }
        } else if (question instanceof TrueFalseQuestion) {
            Button trueButton = new Button("True");
            trueButton.setOnAction(e -> handleAnswer("True"));
            Button falseButton = new Button("False");
            falseButton.setOnAction(e -> handleAnswer("False"));

            optionsBox.getChildren().addAll(trueButton, falseButton);
//Creates "True" and "False" buttons with corresponding click actions.
        }
    }

    private void handleAnswer(String answer) {
        Question question = quizGame.getQuestions().get(currentQuestionIndex);
        if (question.checkAnswer(answer)) {
            quizGame.incrementScore();
        }
        currentQuestionIndex++;
        showQuestion();
/*Checks and Records the Answer:
Gets the current question and verifies if the given answer is correct.
If correct, increments the score using quizGame.incrementScore().
Moves to the next question by incrementing currentQuestionIndex.
Calls showQuestion() to display the next question.
*/
    }

    public void startTimer() {
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
//Starts the Countdown Timer:
//Creates a scheduled executor that runs every second.
//Decrements timeLeft every second and updates the timer label using Platform.runLater (ensures updates are on the UI thread).
//When the timer reaches 0, stops the executor and ends the game.
    }

    public void endGame() {
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
/*Ends the Game:
Calls quizGame.endGame() to finish the game logic.
Stops the timer executor and displays a dialog with the final score.
Tries to save the high score to a file. If it fails, shows an error.
Exits the application.
*/
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
/*Displays an Error Message:
Shows an error dialog with the given message.
Waits for the user to close the dialog before proceeding.
 */
    }
}
/*Purpose: The Main class is the entry point for the quiz game. It sets up the user interface, handles game logic, and manages a timer using JavaFX.
Key Features:
JavaFX Application Setup:

Extends Application to create a graphical user interface (GUI) for the quiz game.
Uses VBox to organize the layout with labels for questions and a timer, and buttons for answer choices.
Quiz Game Integration:

Creates a QuizGame object to load questions from a file and track progress.
Displays questions and their answer options dynamically, based on the question type.
Timer Management:

Runs a 300-second countdown using a ScheduledExecutorService.
Updates the timer in the GUI and ends the game when time runs out.
Answer Handling:

Validates the player's answer.
Updates the score if the answer is correct and moves to the next question.
Game Ending:

Displays the player's score in an alert box when the game ends (either by finishing all questions or the timer running out).
Saves the high score to a file and exits the application.
Error Handling:

Displays error messages using alert boxes for issues like file loading or high score saving.
*/