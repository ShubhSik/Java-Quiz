package edu.sdccd.cisc190;
//Specifies the package where this class resides.
public abstract class Game {//Declares Game as an abstract class.
    private final String gameName;//A private variable to store the name of the game.
    private boolean isRunning;//Tracks if the game is currently running (true) or not (false).

    public Game(String gameName) {
        this.gameName = gameName;
        this.isRunning = false;
    }
//A constructor that sets the game name and initializes isRunning to false (the game starts as "not running").
    public void startGame() {
        isRunning = true;
        System.out.println(gameName + " has started!");
    }
//Starts the game by setting isRunning to true and prints a message like: Game has started!.
    public void endGame() {
        isRunning = false;
        System.out.println(gameName + " has ended.");
    }
//Stops the game by setting isRunning to false and prints a message like: Game has ended.
    public boolean isRunning() {
        return isRunning;
    }
//A placeholder method. Every class that extends Game must define how the game is played.
}
//This Game class provides the basic structure for any game: