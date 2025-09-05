package com.friendprojects.chessapp.ai.eval;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Piece;

public class Evaluator {

    public static final int PHASE_TOTAL = 24;

    public static int material(Board board) {
        int value = 0;
        for (Piece piece : board.getChessBoard().values()) {
            // Black is negative and white is positive in conjunction to standard eval bar metrics
            value += piece.getColour() == Colour.WHITE ? piece.getMaterialValue() : -piece.getMaterialValue();
        }
        return value / 100;
    }

    public static int position(Board board) {
        // Piece-Square Tables (Simplified Evaluation Function), King Safety, Mobility
        int value = 0;
        for (Piece piece : board.getChessBoard().values()) {
            if (piece.getType() == PieceType.KING) {
                value += PieceSquareTables.getPSTSquare(piece.getType(), piece.getPosition(), piece.getColour(), computePhase(board));
            } else {
                value += PieceSquareTables.getPSTSquare(piece.getType(), piece.getPosition(), piece.getColour());
            }
        }
        return value / 100;
    }

    public static int combined(Board board) {
        return material(board) + position(board);
    }

    private static int computePhase(Board board) {
        // Pawn = 0, Knight = 1, Bishop = 1, Rook = 2, Queen = 4, King = 0
        return board.getPieceCount(PieceType.KNIGHT) + board.getPieceCount(PieceType.BISHOP) + board.getPieceCount(PieceType.ROOK) * 2 + board.getPieceCount(PieceType.QUEEN) * 4;
    }
}
