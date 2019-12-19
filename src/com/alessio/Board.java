package com.alessio;

public class Board {
    private Character[][] markers;

    public Board() {
        markers = new Character[][]{ // initiate board
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}};
    }

    public boolean setMarker(Player player, Coordinate markerPos) { //
        if (this.markers[markerPos.row()][markerPos.column()] == ' ') {
            this.markers[markerPos.row()][markerPos.column()] = player.marker();
            return true;
        } else {
            return false;
        }
    }

    public char getMarker(Coordinate position) {
        return this.markers[position.row()][position.column()];
    }

    public void draw(int controls) {
        if (controls == 1) {
            System.out.println("");
            System.out.println(" " + this.markers[0][0] + " | " + this.markers[0][1] + " | " + this.markers[0][2] + "  " + "          1 | 2 | 3 ");
            System.out.println("---+---+---          ---+---+---");
            System.out.println(" " + this.markers[1][0] + " | " + this.markers[1][1] + " | " + this.markers[1][2] + " " + "           4 | 5 | 6 ");
            System.out.println("---+---+---          ---+---+---");
            System.out.println(" " + this.markers[2][0] + " | " + this.markers[2][1] + " | " + this.markers[2][2] + " " + "           7 | 8 | 9 ");
            System.out.println("");
        } else {
            System.out.println("");
            System.out.println(" " + this.markers[0][0] + " | " + this.markers[0][1] + " | " + this.markers[0][2] + "  " + "       A  1 | 2 | 3 ");
            System.out.println("---+---+---          ---+---+---");
            System.out.println(" " + this.markers[1][0] + " | " + this.markers[1][1] + " | " + this.markers[1][2] + " " + "        B  1 | 2 | 3 ");
            System.out.println("---+---+---          ---+---+---");
            System.out.println(" " + this.markers[2][0] + " | " + this.markers[2][1] + " | " + this.markers[2][2] + " " + "        C  1 | 2 | 3 ");
            System.out.println("");
        }
    }

    public void reset() {
        this.markers = new Character[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}};
    }

    public int evaluateState(Player player) { // For three in a return win: 1, draw: 0, loss: -1

        //Check rows for three in a row
        for (int rows = 0; rows < this.markers.length; rows++) {
            if (this.markers[rows][0] != ' ' && this.markers[rows][0] == this.markers[rows][1] && this.markers[rows][1] == this.markers[rows][2]) {
                if (this.markers[rows][0] == player.marker()) {
                    return 10;
                } else {
                    return -10;
                }
            }
        }

        //Check columns for three in a row
        for (int columns = 0; columns < this.markers[0].length; columns++) {
            if (this.markers[0][columns] != ' ' && this.markers[0][columns] == this.markers[1][columns] && this.markers[1][columns] == this.markers[2][columns]) {
                if (this.markers[0][columns] == player.marker()) {
                    return 10;
                } else {
                    return -10;
                }
            }
        }

        //Check diagonals
        if (this.markers[1][1] != ' ' && this.markers[0][0] == this.markers[1][1] && this.markers[1][1] == this.markers[2][2]) {
            if (this.markers[1][1] == player.marker()) {
                return 10;
            } else {
                return -10;
            }
        }
        if (this.markers[1][1] != ' ' && this.markers[0][2] == this.markers[1][1] && this.markers[1][1] == this.markers[2][0]) {

            if (this.markers[1][1] == player.marker()) {
                return 10;
            } else {
                return -10;
            }
        }
        return 0;
    }

    public boolean hasSpacesLeft() {
        //Check draw
        int isEmptyCounter = 0; //initialize counter

        for (int i = 0; i < this.markers.length; i++) { // loop through array and if any cell isEmpty increment counter
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[i][j] == ' ') {
                    isEmptyCounter++;
                }
            }
        }
        if (isEmptyCounter < 1) { // has no spaces left = draw
            return false;
        }
        return true; // has spaces left
    }

    public Coordinate checkRowsForWinningMove(char marker) {
        for (int i = 0; i < markers.length; i++) {
            for (int j = 0; j < markers[i].length; j++) {
                if (markers[i][1] == marker) {
                    if (markers[i][0] == markers[i][1] || markers[i][1] == markers[i][2]) {
                        if (markers[i][0] == ' ') {
                            //System.out.println("going for 3in row");
                            return new Coordinate(i, 0);
                        } else if (markers[i][2] == ' ') {
                            //System.out.println("going for 3in row");
                            return new Coordinate(i, 2);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate checkColumnsForWinningMove(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[1][j] == marker) {
                    if (this.markers[0][j] == this.markers[1][j] || this.markers[1][j] == this.markers[2][j]) {
                        if (this.markers[0][j] == ' ') {
                            //System.out.println("going for 3in col");
                            return new Coordinate(0, j);
                        } else if (this.markers[2][j] == ' ') {
//                            System.out.println("going for 3in col");
                            return new Coordinate(2, j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate checkForwardDiagonalForWin(char marker) {
        if (this.markers[1][1] == marker) {
            if (this.markers[0][0] == this.markers[1][1] || this.markers[1][1] == this.markers[2][2]) {
                if (this.markers[0][0] == ' ') {
//                    System.out.println("going for 3in diagonal");
                    return new Coordinate(0, 0);
                } else if (this.markers[2][2] == ' ') {
//                    System.out.println("going for 3in diagonal");
                    return new Coordinate(2, 2);
                }
            }
        }
        return null;
    }

    public Coordinate checkBackwardDiagonalForWin(char marker) {
        if (this.markers[1][1] == marker) {
            if (this.markers[0][2] == this.markers[1][1] || this.markers[1][1] == this.markers[2][0]) {
                if (this.markers[0][2] == ' ') {
//                    System.out.println("going for 3in diagonal");
                    return new Coordinate(0, 2);
                } else if (this.markers[2][0] == ' ') {
//                    System.out.println("going for 3in diagonal");
                    return new Coordinate(2, 0);
                }
            }
        }
        return null;
    }

    public Coordinate checkColumnsForOffColumnSetForWin(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[0][j] == marker && this.markers[0][j] != ' ') {
                    if (this.markers[0][j] == this.markers[2][j]) {
                        if (this.markers[1][j] == ' ') {
//                            System.out.println("going for 3inrow col");
                            return new Coordinate(1, j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate checkRowsForOffRowSetForWin(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[i][0] == marker && this.markers[i][0] != ' ') {
                    if (this.markers[i][0] == this.markers[i][2]) {
                        if (this.markers[i][1] == ' ') {
//                            System.out.println("going for 3inrow col");
                            return new Coordinate(i, 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate blockOpponentThreeInRows(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[i][1] != ' ' && this.markers[i][1] != marker) {
                    if (this.markers[i][0] == this.markers[i][1] || this.markers[i][1] == this.markers[i][2]) {
                        if (this.markers[i][0] == ' ') {
//                            System.out.println("Blocking row");
                            return new Coordinate(i, 0);
                        } else if (this.markers[i][2] == ' ') {
//                            System.out.println("Blocking row");
                            return new Coordinate(i, 2);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate blockOpponentThreeInRowOffRow(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[i][0] != ' ' && this.markers[i][0] != marker) {
                    if (this.markers[i][0] == this.markers[i][2]) {
                        if (this.markers[i][1] == ' ') {
//                            System.out.println("Blocking row");
                            return new Coordinate(i, 1);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate blockOpponentThreeInCols(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[1][j] != marker && this.markers[1][j] != ' ') {
                    if (this.markers[0][j] == this.markers[1][j] || this.markers[1][j] == this.markers[2][j]) {
                        if (this.markers[0][j] == ' ') {
//                            System.out.println("Blocking col");
                            return new Coordinate(0, j);
                        } else if (this.markers[2][j] == ' ') {
//                            System.out.println("Blocking col");
                            return new Coordinate(2, j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate blockOpponentThreeInColOffCols(char marker) {
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                if (this.markers[0][j] != marker && this.markers[0][j] != ' ') {
                    if (this.markers[0][j] == this.markers[2][j]) {
                        if (this.markers[1][j] == ' ') {
//                            System.out.println("Blocking col");
                            return new Coordinate(1, j);
                        }
                    }
                }
            }
        }
        return null;
    }

    public Coordinate blockForwardDiagonal(char marker) {
        if (this.markers[1][1] != marker && this.markers[1][1] != ' ') {
            if (this.markers[0][0] == this.markers[1][1] || this.markers[1][1] == this.markers[2][2]) {
                if (this.markers[0][0] == ' ') {
//                    System.out.println("Blocking diagonal");
                    return new Coordinate(0, 0);
                } else if (this.markers[2][2] == ' ') {
//                    System.out.println("Blocking diagonal");
                    return new Coordinate(2, 2);
                }
            }
        }
        return null;
    }

    public Coordinate blockDiagonalBackward(char marker) {
        if (this.markers[1][1] != marker && this.markers[1][1] != ' ') {
            if (this.markers[0][2] == this.markers[1][1] || this.markers[1][1] == this.markers[2][0]) {
                if (this.markers[0][2] == ' ') {
//                    System.out.println("Blocking diagonal");
                    return new Coordinate(0, 2);
                } else if (this.markers[2][0] == ' ') {
//                    System.out.println("Blocking diagonal");
                    return new Coordinate(2, 0);
                }
            }
        }
        return null;
    }

    public Coordinate blockDiagonalMiddle(char marker) {
        if (this.markers[1][1] != marker && this.markers[1][1] != ' ') {
            if (this.markers[0][0] == this.markers[2][2] || this.markers[2][0] == this.markers[0][2]) {
                if (this.markers[1][1] == ' ') {
//                    System.out.println("Blocking diagonal middle");
                    return new Coordinate(1, 1);
                }
            }
        }
        return null;
    }

    public Coordinate blockBetweenCorners(char marker) {
        if (this.markers[0][0] != ' ' && this.markers[0][0] != marker && this.markers[0][0] == this.markers[0][2]) {
            if (this.markers[0][1] == ' ') {
//                System.out.println("Blocking between corner");
                return new Coordinate(0, 1);
            }
        } else if (this.markers[0][2] != ' ' && this.markers[0][2] != marker && this.markers[0][2] == this.markers[2][2]) {
            if (this.markers[1][2] == ' ') {
//                System.out.println("Blocking between corner");
                return new Coordinate(1, 2);
            }
        } else if (this.markers[2][0] != ' ' && this.markers[2][0] != marker && this.markers[2][0] == this.markers[2][2]) {
            if (this.markers[2][1] == ' ') {
//                System.out.println("Blocking between corner");
                return new Coordinate(2, 1);
            }
        } else if (this.markers[0][0] != ' ' && this.markers[0][0] != marker && this.markers[0][0] == this.markers[2][0]) {
            if (this.markers[1][0] == ' ') {
//                System.out.println("Blocking between corner");
                return new Coordinate(1, 0);
            }
        }
        return null;
    }

    public Coordinate blockOppositeCornersToAvoidTrap(char marker) {
        if (this.markers[0][0] != marker && this.markers[0][0] != ' ') {
            if (this.markers[2][2] == ' ') {
//                System.out.println("Blocking opposites0");
                return new Coordinate(2, 2);
            } else if (this.markers[0][2] == ' ') {
//                System.out.println("Blocking opposites0");
                return new Coordinate(0, 2);
            } else if (this.markers[2][0] == ' ') {
//                System.out.println("Blocking opposites0");
                return new Coordinate(2, 0);
            }
        }
        if (this.markers[0][2] != marker && this.markers[0][2] != ' ') {
            if (this.markers[2][0] == ' ') {
//                System.out.println("Blocking opposites 1");
                return new Coordinate(2, 0);
            } else if (this.markers[2][2] == ' ') {
//                System.out.println("Blocking opposites 1");
                return new Coordinate(2, 2);
            } else if (this.markers[0][0] == ' ') {
//                System.out.println("Blocking opposites 1");
                return new Coordinate(0, 0);
            }
        }
        if (this.markers[2][0] != marker && this.markers[2][0] != ' ') {
            if (this.markers[0][2] == ' ') {
//                System.out.println("Blocking opposites 2");
                return new Coordinate(0, 2);
            } else if (this.markers[0][0] == ' ') {
//                System.out.println("Blocking opposites 2");
                return new Coordinate(0, 0);
            } else if (this.markers[2][2] == ' ') {
//                System.out.println("Blocking opposites 2");
                return new Coordinate(2, 2);
            }
        }
        if (this.markers[2][2] != marker && this.markers[0][0] != ' ') {
            if (this.markers[0][0] == ' ') {
//                System.out.println("Blocking opposites 3");
                return new Coordinate(0, 0);
            } else if (this.markers[0][2] == ' ') {
//                System.out.println("Blocking opposites 3");
                return new Coordinate(0, 2);
            } else if (this.markers[2][0] == ' ') {
//                System.out.println("Blocking opposites 3");
                return new Coordinate(2, 0);
            }
        }
        return null;
    }

    public Coordinate allElseFailedGetRandomCoordinate(char marker) {
        int randomRow = -1;
        int randomCol = -1;
        do {
            randomRow = (int) Math.floor(Math.random() * 3);
            randomCol = (int) Math.floor(Math.random() * 3);
            if (this.hasSpacesLeft() && this.markers[randomRow][randomCol] == ' ') {
//                System.out.println("doing random");
                return new Coordinate(randomRow, randomCol);
            }
        } while (this.hasSpacesLeft() && this.markers[randomRow][randomCol] != ' ');
//        System.out.println(" returning null");
        return null;
    }

    //MINIMAX ALGORITHM unbeatable AI
    public int minimax(int depth, boolean isMaximizer, Player player) {
        char opponent;
        int score = this.evaluateState(player);

        if (player.marker() == 'X') {
            opponent = 'O';
        } else {
            opponent = 'X';
        }

        // If Maximizer has wins return score
        if (score == 10) {
            return score - depth; // decrement score with path = longer path to victory will have lower score than shorter
        }

        // If Minimizer wins return score
        if (score == -10) {
            return score + depth; // increment score with path = longer path to victory will have lower score than shorter
        }

        //If draw, return score
        if (!this.hasSpacesLeft())
            return 0;

        // If this maximizer's move
        if (isMaximizer) {
            int best = -1000;

            // Loop through cells in all rows and cols
            for (int i = 0; i < this.markers.length; i++) {
                for (int j = 0; j < this.markers[i].length; j++) {
                    // Check if empty
                    if (this.markers[i][j] == ' ') {
                        // Make move
                        this.markers[i][j] = player.marker();

                        // Call minimax again and select highest value
                        best = Math.max(best, minimax(depth + 1, !isMaximizer, player));

                        // Undo the move
                        this.markers[i][j] = ' ';
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else {
            int best = 1000;

            // loop through cells in rows and cols
            for (int i = 0; i < this.markers.length; i++) {
                for (int j = 0; j < this.markers[i].length; j++) {
                    // Check if empty
                    if (this.markers[i][j] == ' ') {
                        // Make the move
                        this.markers[i][j] = opponent;

                        // Call minimax again and select lowest value
                        best = Math.min(best, minimax(
                                depth + 1, !isMaximizer, player));

                        // Undo the move
                        this.markers[i][j] = ' ';
                    }
                }
            }
            return best;
        }
    }

    public Coordinate findBestMove(Player player) {
        int bestValue = -1000;
        Coordinate bestMove = new Coordinate(-1, -1);

        // loop through all cells, evaluate minimax function
        // for all empty cells. Return the cell
        // with best value.
        for (int i = 0; i < this.markers.length; i++) {
            for (int j = 0; j < this.markers[i].length; j++) {
                // Check if empty
                if (this.markers[i][j] == ' ') {
                    // Make the move
                    this.markers[i][j] = player.marker();

                    // evaluate function for this
                    // move.
                    int moveVal = minimax(0, false, player);

                    // Undo the move
                    this.markers[i][j] = ' ';

                    // If the value of move is
                    // more than the best value, then update
                    // bestVal
                    if (moveVal > bestValue) {
                        bestMove.setRow(i);
                        bestMove.setColumn(j);
                        bestValue = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }
}



