package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.PieceType;

import java.util.Objects;

public class Move {
    private final Piece piece;
    private final Position origin;
    private final Position target;
    private final Piece capturedPiece; // Optional as null
    // Use PieceType.QUEEN as placeholder if user input is not finalised to indicate a pawn promotion
    private final PieceType promotionType; // Optional as null
    private final Move castlingRookMove; // Optional as null

    public Move(Piece piece, Position origin, Position target, Piece capturedPiece, PieceType promotionType, Move castlingRookMove) {
        if (!piece.getPosition().equals(origin)) {
            throw new IllegalArgumentException("Move Class: [Piece not found at origin position during creation]");
        }
        this.piece = piece;
        this.origin = origin;
        this.target = target;
        this.capturedPiece = capturedPiece;
        this.promotionType = promotionType;
        this.castlingRookMove = castlingRookMove;
    }

    public Move (Piece piece, Position origin, Position target) {
        this(piece, origin, target, null, null, null);
    }

    public Move (Piece piece, Position origin, Position target, Piece capturedPiece) {
        this(piece, origin, target, capturedPiece, null, null);
    }

    public Move (Piece piece, Position origin, Position target, PieceType promotionType) {
        this(piece, origin, target, null, promotionType, null);
    }

    public Move (Piece piece, Position origin, Position target, Piece capturedPiece, PieceType promotionType) {
        this(piece, origin, target, capturedPiece, promotionType, null);
    }

    public Move (Piece piece, Position origin, Position target, Move castlingRookMove) {
        this(piece, origin, target, null, null, castlingRookMove);
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

    public boolean isPawnPromotion() {
        return this.promotionType != null;
    }

    public Move getCastlingRookMove() {
        return this.castlingRookMove;
    }

    public boolean isCapturing() {
        return this.capturedPiece != null;
    }

    public boolean isCastling() {
        return this.castlingRookMove != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Piece: ").append(this.piece).append("\t| ");
        sb.append("Origin: ").append(this.origin).append("\t| ");
        sb.append("Target: ").append(this.target).append("\t| ");
        sb.append("Capture: ").append(this.capturedPiece).append("\t| ");
        sb.append("Promotion: ").append(this.promotionType).append("\t| ");
        sb.append("Accompanying Rook Castling Move: ").append(this.castlingRookMove).append("\t| ");
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Move move)) return false;
        return this.piece.equals(move.getPiece()) && this.origin.equals(move.getOrigin()) &&
                this.target.equals(move.getTarget()) && Objects.equals(this.capturedPiece, move.getCapturedPiece()) &&
                this.promotionType == move.getPromotionType() && Objects.equals(this.castlingRookMove, move.getCastlingRookMove());
    }
}
