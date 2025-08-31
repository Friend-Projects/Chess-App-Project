package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;

public class HardAI implements AIStrategy {
    private final Minimax minimax = new Minimax(4, Evaluator::combined);

    @Override
    public Move chooseMove(Colour colour, Board board) {
        return minimax.getOptimalMove(colour, board);
    }
}
