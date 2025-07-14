package com.friendprojects.chessapp.model;

public class Position {
    private final int col; // 0-7 (a to h)
    private final int row; // 0-7 (1 to 8)

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public static Position toCoords(String coords) {
        int col = coords.toLowerCase().charAt(0) - 'a';
        int row = coords.charAt(1) - 1;
        return new Position(col, row);
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public Position offset(int colOffset, int rowOffset) {
        int newCol = this.col + colOffset;
        int newRow = this.row + rowOffset;
        return (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) ? null : new Position(this.col + colOffset, this.row + rowOffset);
    }

    public String toAlgebraic() {
        return (char) (this.col + 'a') + String.valueOf((this.row + 1));
    }
}
