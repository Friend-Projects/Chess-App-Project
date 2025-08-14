package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;

import java.util.ArrayList;
import java.util.List;

public class MoveValidator {

    /**
     * Obtain all valid moves available for a chess piece on the given board.
     *
     * @param piece the piece being queried
     * @param board the board that the piece is on
     * @return the list of valid moves for the piece on the board
     */
    public List<Move> getValidMoves(Piece piece, Board board) {
        // Valid moves are chess piece moves that follow piece movement rules and accounts for obstructions but not king safety
        List<Move> validMoves = new ArrayList<>();
        switch (piece.getType()) {
            case PAWN -> validMoves.addAll(getValidPawnMoves(piece, board));
            case KNIGHT -> validMoves.addAll(getValidKnightMoves(piece, board));
            case BISHOP -> validMoves.addAll(getValidDirectionalMoves(piece, board, new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}}));
            case ROOK -> validMoves.addAll(getValidDirectionalMoves(piece, board, new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
            case QUEEN -> validMoves.addAll(getValidDirectionalMoves(piece, board, new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}}));
            case KING -> validMoves.addAll(getValidKingMoves(piece, board));
        }
        return validMoves;
    }

    public boolean isMoveValid(Piece piece, Move move, Board board) {
        return getValidMoves(piece, board).contains(move);
    }

    /**
     * Obtain all valid moves (forward, double forward, captures, and en passant) for a pawn type piece on the board.
     *
     * @param piece the pawn being queried
     * @param board the board that the piece is on
     * @return the list of valid moves for the pawn on the board
     */
    private List<Move> getValidPawnMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        Position origin = piece.getPosition();
        int yOffset = piece.getColour() == Colour.WHITE ? 1 : -1;

        // Standard Forward Move
        Position forward = origin.offset(0, yOffset);
        if (forward != null && !board.isOccupied(forward)) {

            if (forward.getRow() == 7 || forward.getRow() == 0) {
                moves.add(new Move(piece, origin, forward, PieceType.QUEEN));
            } else {
                moves.add(new Move(piece, origin, forward));
            }

            // First Move Double Forward
            Position doubleForward = origin.offset(0, 2 * yOffset);
            if (doubleForward != null && !board.isOccupied(doubleForward) && piece.isUnmoved()) {
                moves.add(new Move(piece, origin, doubleForward));
            }
        }

        // Captures
        int[] xOffset = new int[]{-1, 1};
        for (int offset : xOffset) {
            Position diagonalCapture = origin.offset(yOffset, offset);
            if (diagonalCapture != null && board.isOccupiedByColour(diagonalCapture, piece.getColour().opposite())) {
                if (diagonalCapture.getRow() == 7 || diagonalCapture.getRow() == 0) {
                    moves.add(new Move(piece, origin, diagonalCapture, board.getPieceAt(diagonalCapture), PieceType.QUEEN));
                } else {
                    moves.add(new Move(piece, origin, diagonalCapture, board.getPieceAt(diagonalCapture)));
                }
            }
        }

        // En Passant
        Piece enPassantCapture = board.getEnPassantCapture();
        if (enPassantCapture != null && enPassantCapture.getColour() != piece.getColour()) {
            Position capturePos = enPassantCapture.getPosition();
            int colDiff = Math.abs(capturePos.getCol() - origin.getCol());
            if (capturePos.getRow() == origin.getRow() && colDiff == 1) {
                Position enPassantDiagonal = capturePos.offset(capturePos.getCol(), origin.getRow() + yOffset);
                moves.add(new Move(piece, origin, enPassantDiagonal, enPassantCapture));
            }
        }
        return moves;
    }

    /**
     * Obtain all valid moves for a knight type piece on the board.
     *
     * @param piece the knight being queried
     * @param board the board that the piece is on
     * @return the list of valid moves for the knight on the board
     */
    private List<Move> getValidKnightMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        Position origin = piece.getPosition();
        int[][] kOffset = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] offset : kOffset) {
            Position jump = origin.offset(offset[0], offset[1]);
            if (jump != null && !board.isOccupiedByColour(jump, piece.getColour())) {
                moves.add(new Move(piece, origin, jump, board.getPieceAt(jump)));
            }
        }
        return moves;
    }

    /**
     * Obtain all valid moves for a directional type piece (bishop, rook, and queen) on the board.
     *
     * @param piece the piece being queried
     * @param board the board that the piece is on
     * @param dOffset the offset in the directions the piece move towards
     * @return the list of valid moves for the piece on the board
     */
    private List<Move> getValidDirectionalMoves(Piece piece, Board board, int[][] dOffset) {
        List<Move> moves = new ArrayList<>();
        Position origin = piece.getPosition();
        for (int[] offset : dOffset) {
            Position current = origin.offset(offset[0], offset[1]);
            while (current != null) {
                if (board.isOccupied(current)) {
                    Piece occupied = board.getPieceAt(current);
                    if (occupied.getColour() != piece.getColour()) {
                        moves.add(new Move(piece, origin, current, occupied));
                    }
                    break;
                }
                moves.add(new Move(piece, origin, current));
                current = current.offset(offset[0], offset[1]);
            }
        }
        return moves;
    }

    /**
     * Obtain all valid moves (normal and castling) for a king type piece on the board.
     *
     * @param piece the king being queried
     * @param board the board that the piece is on
     * @return the list of valid moves for the king on the board
     */
    private List<Move> getValidKingMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        Position origin = piece.getPosition();
        int[][] kingOffset = new int[][]{{1, 0}, {1, 1}, {1, -1}, {0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {-1, -1}};
        for (int[] offset : kingOffset) {
            Position target = origin.offset(offset[0], offset[1]);
            if (target != null && !board.isOccupiedByColour(target, piece.getColour())) {
                moves.add(new Move(piece, origin, target, board.getPieceAt(target)));
            }
        }

        // Castling
        if (piece.isUnmoved()) {
            int[] rooksOffset = new int[]{-4, 3};
            Piece queenSideRook = board.getPieceAt(origin.offset(rooksOffset[0], 0));
            if (queenSideRook.isUnmoved() && !board.isOccupied(origin.offset(-3, 0)) && !board.isOccupied(origin.offset(-2, 0)) && !board.isOccupied(origin.offset(-1, 0))) {
                Position kingCastle = origin.offset(-2, 0);
                Position rookCastle = origin.offset(-1, 0);
                Move rookCastling = new Move(queenSideRook, queenSideRook.getPosition(), rookCastle);
                moves.add(new Move(piece, origin, kingCastle, rookCastling));
            }

            Piece kingSideRook = board.getPieceAt(origin.offset(rooksOffset[1], 0));
            if (kingSideRook.isUnmoved() && !board.isOccupied(origin.offset(1, 0)) && !board.isOccupied(origin.offset(2, 0))) {
                Position kingCastle = origin.offset(2, 0);
                Position rookCastle = origin.offset(1, 0);
                Move rookCastling = new Move(kingSideRook, kingSideRook.getPosition(), rookCastle);
                moves.add(new Move(piece, origin, kingCastle, rookCastling));
            }
        }
        return moves;
    }
}
