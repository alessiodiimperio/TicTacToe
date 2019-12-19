package com.alessio;

public class Coordinate {
    private int row;
    private int column;

    public Coordinate(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int row() {
        return this.row;
    }

    public int column() {
        return this.column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String toString() {
        return "Row: " + this.row + " Column: " + this.column;
    }
}
