package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.rules.Rules;
import com.friendprojects.chessapp.util.RandomProvider;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EasyAI implements AIStrategy {
    @Override
    public Move chooseMove(Colour colour, Board board) {
        List<Move> legalMoves = Rules.RULES_ENGINE.getLegalMoves(colour, board);
        List<Node<Move>> candidateMoves = legalMoves.stream().map(move -> new Node<Move>(move, computeMoveValue(move))).toList();
        double totalCost = 0.0;
        for (Node<Move> candidate : candidateMoves) {
            totalCost += candidate.getCost();
        }
        double randomFactor = RandomProvider.getNextDouble() * totalCost;
        double accumulation = 0.0;

        for (Node<Move> candidate : candidateMoves) {
            accumulation += candidate.getCost();
            if (randomFactor <= accumulation) {
                return candidate.getItem();
            }
        }
        // Fallback to best material value move
        return Collections.max(candidateMoves, Comparator.comparingDouble(Node::getCost)).getItem();
    }

    private double computeMoveValue(Move move) {
        return move.isCapturing() ? move.getCapturedPiece().getMaterialValue() + 0.1 : 0.1;
    }
}
