package com.friendprojects.chessapp.enums;

// Material values from Simplified Evaluation Function by Tomasz Michniewski
public enum PieceType {
    PAWN(100),
    KNIGHT(320),
    BISHOP(330),
    ROOK(500),
    QUEEN(900),
    KING(20000);

    private final int materialValue;

    PieceType(int materialValue) {
        this.materialValue = materialValue;
    }

    public int getMaterialValue() {
        return materialValue;
    }

    public String getSymbol() {
        return this == PieceType.KNIGHT ? "N" : Character.toString(this.name().charAt(0));
    }
}
