package com.alessio;

public class AIEasy extends Player{
    private int difficulty;
    private Board board;

    public AIEasy(char marker, Board board) {
        super("Autobot", marker, true);
        this.board = board;
    }

    @Override
    public Coordinate move(int controls) {
        // loop through rows. if 2 in row for opponent, block empty slot
        if (board.blockOpponentThreeInRows(this.marker) != null)
            return board.blockOpponentThreeInRows(this.marker);

        // loop through rows. if 2 off in row block inbetween empty slot
        if (board.blockOpponentThreeInRowOffRow(this.marker) != null)
            return board.blockOpponentThreeInRowOffRow(this.marker);

        // loop through columns. if 2 in row block empty slot
        if (board.blockOpponentThreeInCols(this.marker) != null)
            return board.blockOpponentThreeInCols(this.marker);

        // loop through columns. if 2 offset in row block empty in middle slot
        if (board.blockOpponentThreeInColOffCols(this.marker) != null)
            return board.blockOpponentThreeInColOffCols(this.marker);

        // block diagonal forward
        if (board.blockForwardDiagonal(this.marker) != null)
            return board.blockForwardDiagonal(this.marker);

        //block diagonal backward
        if (board.blockDiagonalBackward(this.marker) != null)
            return board.blockDiagonalBackward(this.marker);

        //block diagonal empty middle
        if (board.blockDiagonalMiddle(this.marker) != null)
            return board.blockDiagonalMiddle(this.marker);

        // If nothing to block do random!
        return board.allElseFailedGetRandomCoordinate(this.marker);
    }
}
