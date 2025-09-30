package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.rules.Rules;
import com.friendprojects.chessapp.util.RandomProvider;

import java.util.List;

public class NoobAI implements AIStrategy {
    @Override
    public Move chooseMove(Colour colour, Board board) {
        List<Move> legalMoves = Rules.RULES_ENGINE.getLegalMoves(colour, board).stream().filter(move -> move.getPiece().getColour() == colour).toList();
        List<Move> captureMoves = legalMoves.stream().filter(Move::isCapturing).toList();
        return captureMoves.isEmpty() ? RandomProvider.select(legalMoves) : RandomProvider.select(captureMoves);
    }
}
