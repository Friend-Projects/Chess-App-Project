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

    // Valid moves are chess piece moves that follow piece movement rules and accounts for obstructions but not king safety
    public List<Move> getValidMoves(Piece piece, Board board) {
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

    private List<Move> getValidPawnMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        int yOffset = piece.getColour() == Colour.WHITE ? 1 : -1;
        Position origin = piece.getPosition();

        // Standard Forward Move
        Position forward = origin.offset(0, yOffset);
        if (forward != null && !board.isOccupied(forward)) {

            if (forward.getRow() == 7 || forward.getRow() == 0) {
                moves.add(new Move(piece, origin, forward, null, PieceType.QUEEN));
            } else {
                moves.add(new Move(piece, origin, forward, null, null));
            }

            // Standard First Move Double Forward
            boolean isFirstMove = (piece.getColour() == Colour.WHITE && origin.getCol() == 1) || (piece.getColour() == Colour.BLACK && piece.getPosition().getCol() == 6);
            Position doubleForward = origin.offset(0, 2 * yOffset);
            if (doubleForward != null && !board.isOccupied(doubleForward) && isFirstMove) {
                moves.add(new Move(piece, origin, doubleForward, null, null));
            }
        }

        // Captures
        int[] xOffset = new int[]{-1, 1};
        for (int offset : xOffset) {
            Position diagonalCapture = origin.offset(yOffset, offset);
            if (diagonalCapture != null && board.isOccupiedByColour(diagonalCapture, piece.getColour())) {
                if (diagonalCapture.getRow() == 7 || diagonalCapture.getRow() == 0) {
                    moves.add(new Move(piece, origin, diagonalCapture, board.getPieceAt(diagonalCapture), PieceType.QUEEN));
                } else {
                    moves.add(new Move(piece, origin, diagonalCapture, board.getPieceAt(diagonalCapture), null));
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
                moves.add(new Move(piece, origin, enPassantDiagonal, enPassantCapture, null));
            }
        }
        return moves;
    }

    private List<Move> getValidKnightMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        int[][] kOffset = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] offset : kOffset) {
            Position jump = piece.getPosition().offset(offset[0], offset[1]);
            if (jump != null && !board.isOccupiedByColour(jump, piece.getColour())) {
                moves.add(new Move(piece, piece.getPosition(), jump, board.getPieceAt(jump), null));
            }
        }
        return moves;
    }

    private List<Move> getValidDirectionalMoves(Piece piece, Board board, int[][] dOffset) {
        List<Move> moves = new ArrayList<>();
        return moves;
    }

    private List<Move> getValidKingMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<>();
        return moves;
    }
}
