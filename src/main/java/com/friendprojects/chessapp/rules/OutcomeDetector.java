package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Game;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Player;

import java.util.ArrayList;
import java.util.List;

public class OutcomeDetector {

    private final RulesEngine rulesEngine;

    public OutcomeDetector(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    /**
     * Chess game ends with player losing by being checkmated.
     *
     * @param player the player checked for checkmate
     * @param game the current chess match being played
     * @return {@code true} if game is lost by checkmate
     */
    public boolean isCheckmate(Player player, Game game) {
        Board board = game.getBoard();
        Colour colour = player.getColor();
        return rulesEngine.isKingInCheck(colour, board) && rulesEngine.getLegalMoves(colour, board).isEmpty();
    }

    /**
     * Chess game ends in draw as player is not in check but has no legal moves.
     *
     * @param player the player checked for stalemate
     * @param game the current chess match being played
     * @return {@code true} if game is drawn by stalemate
     */
    public boolean isDrawByStalemate(Player player, Game game) {
        Board board = game.getBoard();
        Colour colour = player.getColor();
        return !rulesEngine.isKingInCheck(colour, board) && rulesEngine.getLegalMoves(colour, board).isEmpty();
    }

    /**
     * Chess game ends in draw by three-fold repetition.
     *
     * @param game the current chess match being played
     * @return {@code true} if game is drawn by repetition
     */
    public boolean isDrawByRepetition(Game game) {
        List<Move> movesHistory = new ArrayList<>(game.getMoveHistory());
        if (movesHistory.size() < 9) {
            return false;
        } else {
            List<Move> lastThreeTurns = movesHistory.subList(movesHistory.size() - 9, movesHistory.size());

            return true;
        }
    }

    public boolean isDrawBy50MoveRule(Game game) {
        return false;
    }

    public boolean isDrawByInsufficientMaterial(Game game) {
        return false;
    }
}
