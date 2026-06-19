package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.Display;
import com.friendprojects.chessapp.enums.PieceType;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;
    private final EnumMap<PieceType, Integer> pieceCount;

    private Piece enPassantCapture = null;
    private Piece whiteKing;
    private Piece blackKing;

    public Board() {
        this.chessBoard = new HashMap<>();
        this.pieceCount = new EnumMap<>(PieceType.class);
    }

    public Board(Board copy) {
        this.chessBoard = new HashMap<>();
        this.pieceCount = new EnumMap<>(PieceType.class);
        for (Map.Entry<Position, Piece> entry : copy.chessBoard.entrySet()) {
            this.chessBoard.put(entry.getKey(), new Piece(entry.getValue()));
            this.pieceCount.merge(entry.getValue().getType(), 1, Integer::sum);
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
            this.addPieceAt(new Position(col, 1), new Piece(PieceType.PAWN, Colour.WHITE));
            this.addPieceAt(new Position(col, 6), new Piece(PieceType.PAWN, Colour.BLACK));

            Piece whitePiece = new Piece(backRankOrder[col], Colour.WHITE, new Position(col, 0));
            Piece blackPiece = new Piece(backRankOrder[col], Colour.BLACK, new Position(col, 7));
            chessBoard.put(whitePiece.getPosition(), whitePiece);
            chessBoard.put(blackPiece.getPosition(), blackPiece);
            if (backRankOrder[col] == PieceType.KING) {
                whiteKing = whitePiece;
                blackKing = blackPiece;
            }
        }
        this.enPassantCapture = null;
        pieceCount.clear();
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
        if (!pieceToMove.equals(pieceAtOrigin)) {
            throw new IllegalArgumentException("Piece " + pieceAtOrigin.getType() + " instead of " + pieceToMove.getType() + " found");
        }

        removePieceAt(origin);
        pieceToMove.setPosition(target);
        pieceToMove.setToMoved();
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

    public Piece getPieceAt(int col, int row) {
        return this.chessBoard.get(new Position(col, row));
    }

    public Piece getPieceAt(String square) {
        return this.chessBoard.get(Position.toCoords(square));
    }

    public void addPieceAt(Position position, Piece piece) {
        if (piece.getPosition() == null) piece.setPosition(position);
        this.chessBoard.put(position, piece);
        this.pieceCount.merge(piece.getType(), 1, Integer::sum);
    }

    public void removePieceAt(Position position) {
        Piece piece = getPieceAt(position);
        this.chessBoard.remove(position);
        this.pieceCount.merge(piece.getType(), -1, (current, decrement) -> {
            int next = current - decrement;
            return next > 0 ? next : null;
        });
    }

    public int getPieceCount(PieceType type) {
        return this.pieceCount.get(type);
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

    public String display(Display mode) {
        StringBuilder sb = new StringBuilder();

        for (int i = 7; i >= 0; i--) {
            int numEmpty = 0;
            for (int j = 0; j <= 7; j++) {
                Position position = new Position(j, i);
                Piece piece = this.getPieceAt(position);
                if (piece == null) {
                    numEmpty++;
                } else {
                    if (numEmpty > 0) {
                        sb.append(mode == Display.CLI ? " ".repeat(numEmpty) : numEmpty);
                        numEmpty = 0;
                    }
                    sb.append(piece.toFENChar());
                }
            }
            if (numEmpty > 0) {
                sb.append(mode == Display.CLI ? " ".repeat(numEmpty) : numEmpty);
            }
            sb.append(mode == Display.CLI ? '\n' : '/');
        }

        return sb.toString();
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
