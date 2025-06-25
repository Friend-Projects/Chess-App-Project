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

    public String toAlgebraic() {
        return (char) (this.col + 'a') + String.valueOf((this.row + 1));
    }
}
