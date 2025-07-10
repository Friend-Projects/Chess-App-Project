package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;

import java.util.List;

public class MoveValidator {

    // Valid moves are chess piece moves that follow piece movement rules and accounts for obstructions
    public List<Move> getValidMoves(Piece piece, Board board) {
        return null;
    }

    public boolean isMoveValid(Piece piece, Move move, Board board) {
        return getValidMoves(piece, board).contains(move);
    }

    private List<Move> getValidPawnMoves(Piece piece, Board board) {
        return null;
    }

    private List<Move> getValidDirectionalMoves(Piece piece, Board board) {
        return null;
    }

    private List<Move> getValidKingMoves(Piece piece, Board board) {
        return null;
    }
}
