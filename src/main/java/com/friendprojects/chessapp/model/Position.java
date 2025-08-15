package com.friendprojects.chessapp.model;

public class Position {
    private final int col; // 0-7 (a to h)
    private final int row; // 0-7 (1 to 8)

    public Position(int col, int row) {
        if (col < 0 || col > 7 || row < 0 || row > 7) {
            throw new IllegalArgumentException("Position Class: [Column and row values out of bounds during creation]");
        }
        this.col = col;
        this.row = row;
    }

    /**
     * Converts the standard chess board coordinates to an {@code Position} instance.
     *
     * @param coords standard chess board coordinates
     * @return a {@code Position} representation of the chess board coordinates
     */
    public static Position toCoords(String coords) {
        int col = coords.toLowerCase().charAt(0) - 'a';
        int row = Character.getNumericValue(coords.charAt(1)) - 1;
        return new Position(col, row);
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    /**
     * Create a new position offset by a coordinate from the original position.
     *
     * @param colOffset the file change differential
     * @param rowOffset the rank change differential
     * @return a new {@code Position} offset from the original
     */
    public Position offset(int colOffset, int rowOffset) {
        int newCol = this.col + colOffset;
        int newRow = this.row + rowOffset;
        return (newCol > 7 || newCol < 0 || newRow > 7 || newRow < 0) ? null : new Position(this.col + colOffset, this.row + rowOffset);
    }

    public String toAlgebraic() {
        return (char) (this.col + 'a') + String.valueOf((this.row + 1));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Col: ").append(this.col).append("\t| ");
        sb.append("Row: ").append(this.row).append("\t| ");
        sb.append("Square: ").append(toAlgebraic());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Position)) return false;
        Position pos = (Position) object;
        return this.col == pos.getCol() && this.row == pos.getRow();
    }
}
