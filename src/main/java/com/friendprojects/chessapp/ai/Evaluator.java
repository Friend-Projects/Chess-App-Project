package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Piece;

public class Evaluator {

    public static int material(Board board) {
        int boardValue = 0;
        for (Piece piece : board.getChessBoard().values()) {
            // Black is negative and white is positive in conjunction to standard eval bar metrics
            boardValue += piece.getColour() == Colour.WHITE ? piece.getMaterialValue() : -piece.getMaterialValue();
        }
        return boardValue;
    }

    public static int position(Board board) {
        return 0;
    }

    public static int combined(Board board) {
        return 0;
    }
}
