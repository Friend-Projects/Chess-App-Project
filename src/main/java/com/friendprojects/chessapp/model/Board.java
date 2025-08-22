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

    public Board(Board copy) {
        this.chessBoard = new HashMap<>();
        for (Map.Entry<Position, Piece> entry : copy.chessBoard.entrySet()) {
            this.chessBoard.put(entry.getKey(), new Piece(entry.getValue()));
        }
        this.enPassantCapture = copy.enPassantCapture == null ? null : new Piece(copy.enPassantCapture);
        this.whiteKing = new Piece(copy.whiteKing);
        this.blackKing = new Piece(copy.blackKing);
    }

    /**
     * Reset and create a blank chess board in the starting position.
     */
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

    /**
     * Changes the current state of the board by applying the given move.
     *
     * @param move the chess move to be executed
     */
    public void executeMove(Move move) {
        Position origin = move.getOrigin();
        Position target = move.getTarget();
        Piece pieceToMove = move.getPiece();
        Piece pieceAtOrigin = chessBoard.get(origin);

        if (!chessBoard.containsKey(origin)) {
            throw new IllegalArgumentException("No " + pieceToMove.getType() + " at position " + origin.toAlgebraic());
        }
        if (pieceToMove.getType() != pieceAtOrigin.getType()) {
            throw new IllegalArgumentException("Piece " + pieceAtOrigin.getType() + " instead of " + pieceToMove.getType() + " found");
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("En Passant Capture: ").append(this.enPassantCapture).append("\t| ");
        sb.append("White King: ").append(this.whiteKing).append("\t| ");
        sb.append("Black King: ").append(this.blackKing).append("\t| ");
        sb.append("Chess Board: ").append(this.chessBoard);
        sb.append("]");
        return sb.toString();
    }
}
