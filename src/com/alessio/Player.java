package com.alessio;


public abstract class Player {
    private int wins;
    private String name;
    protected char marker;
    private boolean isMachine;

    //Human Constructor
    public Player(String name, char marker) {
        this.name = name;
        this.marker = marker;
        this.wins = 0;
        this.isMachine = false;
    }

    // AI Constructor
    public Player(String name, char marker, boolean isMachine) {
        this.name = name;
        this.marker = marker;
        this.wins = 0;
        this.isMachine = isMachine;
    }

    public String getName() { // Return this.players name
        return this.name;
    }

    public char marker() {
        return this.marker;
    }

    public void addWin() { //Increment this.players wins
        this.wins++;
    }

    public int getWins() { // return this.players total wins
        return this.wins;
    }

    public void resetWins() {
        this.wins = 0;
    }

    public boolean getIsMachine() {
        return this.isMachine;
    }

    public abstract Coordinate move(int controls);

    public String toString() { // return this.player name and score as string
        return this.getName() + " is " + this.marker + " has " + this.wins + " wins!";
    }
}
