
package edu.sdccd.cisc190;
/**
 * Abstract base class that provides the structure for any game.
 * <p>
 * This class includes core functionality such as starting, stopping, 
 * and checking the status of the game. Subclasses are required to 
 * define specific gameplay behavior by implementing their own play logic.
 * </p>
 */
public abstract class Game {
    /**
     * The name of the game. 
     */
    private final String gameName;
    /**
     * Tracks whether the game is currently running. 
     */

    private boolean isRunning;

    /**
     * Constructor that initializes the game with a specified name.
     *
     * @param gameName the name of the game.
     */
    public Game(String gameName) {
        this.gameName = gameName;
        this.isRunning = false;
    }
    /**
     * Starts the game by setting the status to running and printing a start message.
     */
    public void startGame() {
        isRunning = true;
        System.out.println(gameName + " has started!");
    }
    /**
     * Ends the game by setting the status to not running and printing an end message.
     */
    public void endGame() {
        isRunning = false;
        System.out.println(gameName + " has ended.");
    }
    /**
     * Checks whether the game is currently running.
     *
     * @return {@code true} if the game is running, {@code false} otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }
}