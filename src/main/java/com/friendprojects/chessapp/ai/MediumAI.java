package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;

public class MediumAI implements AIStrategy {
    private final Minimax minimax = new Minimax(2, Evaluator::material);

    @Override
    public Move chooseMove(Colour colour, Board board) {
        return minimax.getOptimalMove(colour, board);
    }
}
