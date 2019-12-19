package com.alessio;

import javax.print.attribute.standard.PrinterMakeAndModel;

public class AIMedium extends Player {
    private int difficulty;
    private Board board;

    public AIMedium(char marker, Board board) {
        super("Autobot", marker, true);
        this.difficulty = difficulty;
        this.board = board;
    }

    @Override
    public Coordinate move(int controls) {
            int randomRow = 0;
            int randomCol = 0;

            // loop through rows. if 2 in row set marker for win
            if (board.checkRowsForWinningMove(this.marker) != null)
                return board.checkRowsForWinningMove(this.marker);

            // loop through columns. if 2 in row set marker for win
            if (board.checkColumnsForWinningMove(this.marker) != null)
                return board.checkColumnsForWinningMove(this.marker);

            // check diagonal 'backslash' for 2 in row set marker for win
            if (board.checkForwardDiagonalForWin(this.marker) != null)
                return board.checkForwardDiagonalForWin(this.marker);

            //check diagonal 'forwardslash' for 2 in row, set marker for win
            if (board.checkBackwardDiagonalForWin(this.marker) != null)
                return board.checkBackwardDiagonalForWin(this.marker);

            // loop through columns. if 2 offset in row assign empty in middle slot for win
            if (board.checkColumnsForOffColumnSetForWin(this.marker) != null)
                return board.checkColumnsForOffColumnSetForWin(this.marker);

            // loop through rows. if 2 offset in row assign empty in between slot for win
            if (board.checkRowsForOffRowSetForWin(this.marker) != null)
                return board.checkRowsForOffRowSetForWin(this.marker);

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

            // Check corners and block in between corner markers
            if (board.blockBetweenCorners(this.marker) != null)
                return board.blockBetweenCorners(this.marker);

            // check corners and block opposite corner markers before locked in unwinable state.
            if (board.blockOppositeCornersToAvoidTrap(this.marker) != null)
                return board.blockOppositeCornersToAvoidTrap(this.marker);

            return board.allElseFailedGetRandomCoordinate(this.marker);
    }
}