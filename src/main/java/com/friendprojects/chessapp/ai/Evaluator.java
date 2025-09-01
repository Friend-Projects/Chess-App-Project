package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Piece;

public class Evaluator {

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
            value += PieceSquareTables.getPSTSquare(piece.getType(), piece.getPosition(), piece.getColour());
        }
        return value / 100;
    }

    public static int combined(Board board) {
        return 0;
    }
}
