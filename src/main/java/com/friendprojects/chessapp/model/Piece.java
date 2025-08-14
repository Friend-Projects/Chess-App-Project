package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;

public class Piece {

    private final PieceType pieceType;
    private final Colour colour;
    private Position position;
    private boolean isUnmoved = true; // Flag for if piece is in original location without moving

    public Piece(PieceType pieceType, Colour colour) {
        this.pieceType = pieceType;
        this.colour = colour;
        this.position = null;
    }

    public Piece(PieceType pieceType, Colour colour, Position position) {
        this.pieceType = pieceType;
        this.colour = colour;
        this.position = position;
    }

    public Piece(Piece copy) {
        this.pieceType = copy.pieceType;
        this.colour = copy.colour;
        this.position = copy.position;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean isUnmoved() {
        return this.isUnmoved;
    }

    public PieceType getType() {
        return this.pieceType;
    }

    public int getMaterialValue() {
        return this.pieceType.getMaterialValue();
    }

    public Colour getColour() {
        return this.colour;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setToMoved() {
        this.isUnmoved = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Type: ").append(this.pieceType).append("\t| ");
        sb.append("Colour: ").append(this.colour).append("\t| ");
        sb.append("Position: ").append(this.position.toAlgebraic()).append("\t| ");
        sb.append("Unmoved: ").append(this.isUnmoved);
        sb.append("]");
        return sb.toString();
    }
}
