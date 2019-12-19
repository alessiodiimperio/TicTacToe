package com.alessio;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean playing = true; // variable for game loop
        boolean gameover = false; // variable for rematch loop
        boolean markerIsSet = false; //validate marker positioned to exit loop for input request
        int moveScore = 0; // win 1, loss -1, draw 0 for minimax algorithm
        int rounds = 0; // best of rounds per game
        int controls = 1; // control scheme selection

        Board board = new Board(); // Create tic tac toe board

        ArrayList<Player> players = initialize(board); // create Arraylist with Players for game.

        controls = controls(); // request control scheme 1-9 or a1-c3

        rounds = getRounds(); // Get rounds to play best of x rounds
        while (!gameover) { //Restart playing loop if rematch is selected.
            while (playing) { //Loop through game until user exits or game is won.
                for (Player player : players) {
                    if (moveScore < 1) { // If returning from game end for second player in loop skip the following.
                        board.draw(controls); // Draw out tic tac toe board
                        do {
                            Coordinate move = player.move(controls); // Take user input and set marker att given coordinate.
                            if (board.hasSpacesLeft() && move != null) {
                                markerIsSet = board.setMarker(player, move); // Try move if success bool returns true and ends loop else request new coordinate.
                            }
                            if (board.hasSpacesLeft() && markerIsSet == false) { // if game is not draw and marker has not been set return print of incorrect entry
                                System.out.println("This placement is already taken, try again.");
                            }
                            if (!board.hasSpacesLeft()) {//if there is a draw, exit loop
                                markerIsSet = true; // stop loop
                                board.draw(controls); // Draw board with entered new marker per turn
                            }
                        } while (!markerIsSet);


                    }
                    moveScore = board.evaluateState(player); // iterate through different possible winning combination and return 1 for win 0 for draw or -1 for loss.

                    if (moveScore > 0) { // If user wins add point to wins and show current scores
                        System.out.println(player.getName() + " wins round!");
                        board.draw(controls);
                        player.addWin();
                        printScores(players);
                        board.reset();
                    }
                    if (!board.hasSpacesLeft()) { //if game ended with draw
                        System.out.println("Round ended in draw!");
                        printScores(players);
                        board.reset();
                    }
                    // if player has more than half the rounds or both players have shared total rounds = game is draw end game loop
                    if (player.getWins() > (Math.floor(rounds / 2.0)) || gameDraw(players, rounds)) {
                        playing = false;
                    }
                }
            }
            if (gameDraw(players, rounds)) { // if game is draw print draw
                System.out.println("The game has ended with a draw!");
            } else {
                Player winner = getWinner(players); // print winners
                System.out.println("Congratulations " + winner.getName() + "! " + winner.getName() + " has won the game with " + winner.getWins() + " of " + rounds + " rounds!"); // get winner with most points
            }

            gameover = rematch(); // ask for rematch
            if (!gameover) { // if rematch is requested ask for rounds and reassign variables to original values.
                board.reset();
                for (Player player : players) {
                    player.resetWins();
                }
                rounds = getRounds(); // ask for new best of.. rounds

                moveScore = 0; //original value
                playing = true; //original value
                markerIsSet = false;//original value
            }
        }
    }

    /************************************************************
     * INPUT AND EXCEPTIONS HANDLING
     */
    public static void promptContinue() { // Prompt user interaction to continue.
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press key to continue...");
        scanner.nextLine();
    }

    public static int getInputMenuSelection(int menuMinNr, int menuMaxNr) { // get input from user for switch selector with exception control.
        Scanner scanner = new Scanner(System.in);
        int selector = 0;
        while (true) { // loop until valid entry entered
            try {
                String string = scanner.nextLine();
                selector = Integer.parseInt(string);
                if (selector <= menuMaxNr && selector >= menuMinNr) {
                    return selector;
                } else if (selector < menuMinNr || selector > menuMaxNr) { //Keep selection in bounds
                    System.out.println("Invalid entry, try again: ");
                }
            } catch (Exception e) {
                System.out.println("Invalid entry, try again: ");

            }
        }
    }

    public static String getStringInput() { //get input from user with exception try/catch
        String input;
        Scanner scanner = new Scanner(System.in);
        while (true) { // Exception control //
            try {
                input = scanner.nextLine();
                return input;
            } catch (Exception e) {
                System.out.println("We got an exception " + e);
            }
        }
    }

    // Get best of rounds to play
    public static int getRounds() {
        System.out.println("Best of how many rounds? 1-10");
        int rounds = getInputMenuSelection(1, 10);
        System.out.println("Best of " + rounds + " wins the game.");
        return rounds;
    }

    // Check if game is draw
    public static boolean gameDraw(ArrayList<Player> players, int rounds) { // if players have total nr of rounds game is draw
        int player1wins = players.get(0).getWins();
        int player2wins = players.get(1).getWins();
        if (player1wins + player2wins == rounds && rounds % 2 != 1) { // only applicable on even rounds
            return true;
        } else {
            return false;
        }
    }

    // return winner with most rounds
    public static Player getWinner(ArrayList<Player> players) {
        int highScore = 0;
        for (Player player : players) {
            if (player.getWins() > highScore) {
                highScore = player.getWins();
            }
        }
        for (Player player : players) {
            if (player.getWins() == highScore) {
                return player;
            }
        }
        return null;
    }

    /************************************************************
     * INITIALIZE AND CREATE OBJECTS
     */
    //Create players for single or multiplayer5
    public static ArrayList<Player> initialize(Board board) {
        Player player1;
        Player player2;
        int selector = 0;
        ArrayList<Player> players = new ArrayList<>();

        System.out.println("Welcome to TicTacToe - Three in a row!");
        System.out.println("--------------------------");
        System.out.println("1. Single player");
        System.out.println("2. Two players");

        while (selector != 1 && selector != 2) //Exception control//
            selector = getInputMenuSelection(1, 2);
        switch (selector) {
            case 1:
                player1 = createPlayer('X'); //create player for single player game.
                System.out.println("Select difficulty:");
                System.out.println("1. Easy");
                System.out.println("2. Medium");
                System.out.println("3. Impossible");

                int difficulty = getInputMenuSelection(1, 3); // create AI player to user difficulty

                if (difficulty == 1) {
                    player2 = new AIEasy('O', board); // Create Easy AI
                } else if (difficulty == 2) {
                    player2 = new AIMedium('O', board); // Create Medium AI
                } else {
                    player2 = new AIHard('O', board); // Create Minimax
                }
                players.add(player1);
                players.add(player2);
                return players;
            case 2:
                player1 = createPlayer('X'); // Create player1
                player2 = createPlayer('O'); // Create player2
                players.add(player1);
                players.add(player2);
                return players;
            default:
                System.out.println("Invalid entry, Try again:"); // Reply menu selection not valid.. reloop menu.
        }
        return players;
    }

    public static boolean rematch() { // Ask if rematch is wanted
        int selection;
        System.out.println("Would you like a rematch?\n1. Yes\n2. No");
        selection = getInputMenuSelection(1, 2);
        if (selection == 1) { // if rematch is selected
            return false;
        } else {
            System.out.println("Thank you for playing!");
            return true;
        }

    }

    public static Player createPlayer(char marker) { // create player

        System.out.println("Enter name for player " + marker + ": ");
        String name = getStringInput(); // Get player name to variable with exception control

        Player player = new Human(name, marker); // create player with name
        return player;
    }

    public static int controls() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your preferred control scheme:        ");
        System.out.println("                                             ");
        System.out.println("      1 | 2 | 3                A  1 | 2 | 3  ");
        System.out.println("     ---+---+---                 ---+---+--- ");
        System.out.println("      4 | 5 | 6                B  1 | 2 | 3  ");
        System.out.println("     ---+---+---                 ---+---+--- ");
        System.out.println("      7 | 8 | 9                C  1 | 2 | 3  ");
        System.out.println("                                             ");
        System.out.println("  1. 1-9                 2. A1-C3            ");
        return getInputMenuSelection(1, 2);
    }


    /************************************************************
     * PER TURN EVENT IN ORDER OF SUCCESSION
     */

    public static void printScores(ArrayList<Player> players) {
        System.out.println("Current score: "); // Print current score
        for (Player player : players) {
            System.out.println(player.getName() + " has " + player.getWins() + " wins");
        }
        System.out.println("--------------------------");
        promptContinue();//Prompt for user key press to continue next round.
    }
}

// Bugs:
/*

 */

// Status:
/*

 */