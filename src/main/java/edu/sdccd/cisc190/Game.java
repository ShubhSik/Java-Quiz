package edu.sdccd.cisc190;

public abstract class Game {
    private String gameName;
    private boolean isRunning;

    public Game(String gameName) {
        this.gameName = gameName;
        this.isRunning = false;
    }

    public void startGame() {
        isRunning = true;
        System.out.println(gameName + " has started!");
    }

    public void endGame() {
        isRunning = false;
        System.out.println(gameName + " has ended.");
    }

    public boolean isRunning() {
        return isRunning;
    }

    public abstract void play();
}
