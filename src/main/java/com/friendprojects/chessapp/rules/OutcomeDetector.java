package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutcomeDetector {

    private final RulesEngine rulesEngine;

    public OutcomeDetector(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    /**
     * Chess game ends with player losing by being checkmated.
     *
     * @param colour the side checked for checkmate
     * @param board the current state of the board
     * @return {@code true} if game is lost by checkmate
     */
    public boolean isCheckmate(Colour colour, Board board) {
        return rulesEngine.isKingInCheck(colour, board) && rulesEngine.getLegalMoves(colour, board).isEmpty();
    }

    /**
     * Chess game ends in draw as player is not in check but has no legal moves.
     *
     * @param colour the side checked for checkmate
     * @param board the current state of the board
     * @return {@code true} if game is drawn by stalemate
     */
    public boolean isDrawByStalemate(Colour colour, Board board) {
        return !rulesEngine.isKingInCheck(colour, board) && rulesEngine.getLegalMoves(colour, board).isEmpty();
    }

    /**
     * Chess game ends in draw by three-fold repetition.
     *
     * @param game the current chess match being played
     * @return {@code true} if game is drawn by repetition
     */
    public boolean isDrawByRepetition(Game game) {
        return game.getPartialFENHistory().values().stream().anyMatch(count -> count >= 3);
    }

    /**
     * Chess game ends in draw by the 50 move rule where 50 half moves were played with no pawn advance or capture.
     *
     * @param game the current chess match being played
     * @return {@code true} if game is drawn by 50 moves
     */
    public boolean isDrawBy50MoveRule(Game game) {
        return game.getHalfMoveClock() >= 50;
    }

    /**
     * Chess game ends in draw by insufficient material to checkmate the opponent.
     *
     * @param game the current chess match being played
     * @return {@code true} if game is drawn by insufficient material
     */
    public boolean isDrawByInsufficientMaterial(Game game) {
        Board board = game.getBoard();
        Map<Position, Piece> chessBoard = board.getChessBoard();

        if (board.getKing(Colour.WHITE) == null || board.getKing(Colour.BLACK) == null) {
            return false;
        }
        List<Piece> nonKingPieces = chessBoard.values().stream().filter(piece -> piece.getType() != PieceType.KING).toList();

        if (nonKingPieces.isEmpty()) {
            return true;
        }
        else if (nonKingPieces.size() == 1) {
            return isMinorPiece(nonKingPieces.get(0));
        }
        else if (nonKingPieces.size() == 2) {
            Piece one = nonKingPieces.get(0);
            Piece two = nonKingPieces.get(1);
            if (one.getType() == PieceType.KNIGHT && two.getType() == PieceType.KNIGHT) return true;
            if (isMinorPiece(one) && isMinorPiece(two) && one.getColour() != two.getColour()) return true;
        }
        return false;
    }

    private boolean isMinorPiece(Piece piece) {
        return piece.getType() == PieceType.KNIGHT || piece.getType() == PieceType.BISHOP;
    }
}
