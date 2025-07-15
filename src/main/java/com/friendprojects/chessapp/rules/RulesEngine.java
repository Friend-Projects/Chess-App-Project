package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.model.*;

import java.util.List;

public class RulesEngine {

    private final MoveValidator moveValidator;

    public RulesEngine(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    public List<Move> getLegalMoves(Player player, Game game) {
        return null;
    }

    public List<Move> getLegalMovesForPiece(Piece piece, Game game) {
        return null;
    }

    public boolean isMoveLegal(Move move, Game game) {
        return false;
    }

    public boolean isKingInCheck(Board board) {
        return false;
    }
}
