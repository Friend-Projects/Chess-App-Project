package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    private Piece enPassantCapture = null;
    private Piece whiteKing;
    private Piece blackKing;

    public Board() {
        this.chessBoard = new HashMap<>();
    }

    public void setupBoard() {
        this.chessBoard.clear();
        PieceType[] backRankOrder = {PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN, PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK};
        for (int col = 0; col < 8; col++) {
            Position whitePawnPos = new Position(col, 1);
            Position blackPawnPos = new Position(col, 6);
            chessBoard.put(whitePawnPos, new Piece(PieceType.PAWN, Colour.WHITE, whitePawnPos));
            chessBoard.put(blackPawnPos, new Piece(PieceType.PAWN, Colour.BLACK, blackPawnPos));

            Piece whitePiece = new Piece(backRankOrder[col], Colour.WHITE, new Position(col, 0));
            Piece blackPiece = new Piece(backRankOrder[col], Colour.BLACK, new Position(col, 7));
            chessBoard.put(whitePiece.getPosition(), whitePiece);
            chessBoard.put(blackPiece.getPosition(), blackPiece);
            if (backRankOrder[col] == PieceType.KING) {
                whiteKing = whitePiece;
                blackKing = blackPiece;
            }
        }
    }

    public void applyMove(Move move) {
        Position origin = move.getOrigin();
        Position target = move.getTarget();
        Piece pieceToMove = move.getPiece();

        Piece pieceAtOrigin = chessBoard.get(origin);
        if (pieceToMove.getType() != pieceAtOrigin.getType()) {
            throw new IllegalArgumentException("Piece " + pieceToMove.getType() + " instead of " + pieceAtOrigin.getType() + " found");
        }
        if (!chessBoard.containsKey(origin)) {
            throw new IllegalArgumentException("No " + pieceToMove.getType() + " at position " + origin.toAlgebraic());
        }

        removePieceAt(origin);
        pieceAtOrigin.setPosition(target);
        addPieceAt(target, pieceToMove);
    }

    public void setEnPassantCapture(Piece piece) {
        this.enPassantCapture = piece;
    }

    public Piece getEnPassantCapture() {
        return this.enPassantCapture;
    }

    public Piece getKing(Colour colour) {
        return colour == Colour.WHITE ? whiteKing : blackKing;
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(this.chessBoard);
    }

    public Piece getPieceAt(Position position) {
        return this.chessBoard.get(position);
    }

    public void addPieceAt(Position position, Piece piece) {
        this.chessBoard.put(position, piece);
    }

    public void removePieceAt(Position position) {
        this.chessBoard.remove(position);
    }

    public boolean isOccupied(Position position) {
        return this.chessBoard.containsKey(position);
    }

    public boolean isOccupiedByColour(Position position, Colour colour) {
        if (isOccupied(position)) {
            return getPieceAt(position).getColour() == colour;
        }
        return false;
    }
}
