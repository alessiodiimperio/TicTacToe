package com.alessio;

public class AIHard extends Player{
    private int difficulty;
    private Board board;

    public AIHard(char marker, Board board) {
        super("Autobot", marker, true);
        this.board = board;
    }

    @Override
    public Coordinate move(int controls) {
        Coordinate bestMove = board.findBestMove(this);
        return bestMove;
    }
}
