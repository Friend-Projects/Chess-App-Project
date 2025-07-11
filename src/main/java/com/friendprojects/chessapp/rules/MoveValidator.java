package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.enums.Colour;
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
            case BISHOP -> validMoves.addAll(getValidDirectionalMoves(piece, board));
            case ROOK -> validMoves.addAll(getValidDirectionalMoves(piece, board));
            case QUEEN -> validMoves.addAll(getValidDirectionalMoves(piece, board));
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
            moves.add(new Move(piece, origin, forward, null, null));

            // Standard First Move Double Forward
            boolean isFirstMove = (piece.getColour() == Colour.WHITE && origin.getCol() == 1) || (piece.getColour() == Colour.BLACK && piece.getPosition().getCol() == 6);
            Position doubleForward = origin.offset(0, 2 * yOffset);
            if (doubleForward != null && !board.isOccupied(doubleForward) && isFirstMove) {
                moves.add(new Move(piece, origin, doubleForward, null, null));
            }
        }

        // Captures

        // En Passant

        // Promotion

        return moves;
    }

    private List<Move> getValidKnightMoves(Piece piece, Board board) {
        return null;
    }

    private List<Move> getValidDirectionalMoves(Piece piece, Board board) {
        return null;
    }

    private List<Move> getValidKingMoves(Piece piece, Board board) {
        return null;
    }
}
