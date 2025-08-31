package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.rules.Rules;

import java.util.List;
import java.util.function.ToIntFunction;

public class Minimax {

    private final int maxDepth;
    private final ToIntFunction<Board> evaluator;

    public Minimax(int maxDepth, ToIntFunction<Board> evaluator) {
        this.maxDepth = maxDepth;
        this.evaluator = evaluator;
    }

    public Move getOptimalMove(Colour colour, Board board) {
        List<Move> legalMoves = Rules.RULES_ENGINE.getLegalMoves(colour, board).stream().filter(move -> move.getPiece().getColour() == colour).toList();
        boolean isWhite = colour == Colour.WHITE;
        Move bestMove = null;
        int bestValue = isWhite ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (Move legalMove : legalMoves) {
            Board dummy = new Board(board);
            dummy.executeMove(legalMove);
            // n-ply = maxDepth - 1, as true 1-ply is the current legal moves iteration
            int newValue = minimax(dummy, 0, this.maxDepth - 1, !isWhite);

            if (isWhite && newValue > bestValue) {
                bestValue = newValue;
                bestMove = legalMove;
            } else if (!isWhite && newValue < bestValue) {
                bestValue = newValue;
                bestMove = legalMove;
            }
        }
        return bestMove;
    }

    private int minimax(Board board, int currentDepth, int maxDepth, boolean isWhite) {
        if (currentDepth == maxDepth) {
            return evaluator.applyAsInt(board);
        }
        Colour colour = isWhite ? Colour.WHITE : Colour.BLACK;
        List<Move> legalMoves = Rules.RULES_ENGINE.getLegalMoves(colour, board);

        if (isWhite) {
            int maxValue = Integer.MIN_VALUE;
            for (Move legalMove : legalMoves) {
                Board dummy = new Board(board);
                dummy.executeMove(legalMove);
                int value = minimax(board, currentDepth + 1, maxDepth, false);
                maxValue = Math.max(maxValue, value);
            }
            return maxValue;
        } else {
            int minValue = Integer.MAX_VALUE;
            for (Move legalMove : legalMoves) {
                Board dummy = new Board(board);
                dummy.executeMove(legalMove);
                int value = minimax(board, currentDepth + 1, maxDepth, true);
                minValue = Math.min(minValue, value);
            }
            return minValue;
        }
    }
}
