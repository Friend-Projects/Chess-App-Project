package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.PieceType;

public class Move {
    private final Piece piece;
    private final Position origin;
    private final Position target;
    private final Piece capturedPiece; // Optional as null
    private final PieceType promotionType; // Optional as null

    public Move(Piece piece, Position origin, Position target, Piece capturedPiece, PieceType promotionType) {
        this.piece = piece;
        this.origin = origin;
        this.target = target;
        this.capturedPiece = capturedPiece;
        this.promotionType = promotionType;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public Position getOrigin() {
        return this.origin;
    }

    public Position getTarget() {
        return this.target;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

    public PieceType getPromotionType() {
        return this.promotionType;
    }

    // TODO: Consider Captures, Disambiguating Moves, Promotion, Castling, Check
    public String toAlgebraic() {
        if (this.piece.getType() == PieceType.KNIGHT) {
            return "N" + this.target.toAlgebraic();
        } else {
            return this.piece.getType().name().charAt(0) + this.target.toAlgebraic();
        }
    }
}
