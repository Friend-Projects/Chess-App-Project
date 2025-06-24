package com.friendprojects.chessapp.model;

public class Piece {

    private final PieceType pieceType;
    private final Colour colour;
    private Position position;

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

    public Position getPosition() {
        return this.position;
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

    public void setPosition() {
        this.position = position;
    }
}
