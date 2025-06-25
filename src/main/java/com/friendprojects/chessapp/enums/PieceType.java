package com.friendprojects.chessapp.enums;

public enum PieceType {
    PAWN(1),
    KNIGHT(3),
    BISHOP(3),
    ROOK(5),
    QUEEN(9),
    KING(1000);

    private final int materialValue;

    PieceType(int materialValue) {
        this.materialValue = materialValue;
    }

    public int getMaterialValue() {
        return materialValue;
    }
}
