package com.friendprojects.chessapp.model;

public class Piece {

    private final PieceType pieceType;
    private final Colour colour;

    public Piece(PieceType pieceType, Colour colour) {
        this.pieceType = pieceType;
        this.colour = colour;
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
}
