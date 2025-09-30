package com.friendprojects.chessapp.ai;

import com.friendprojects.chessapp.ai.eval.Evaluator;
import com.friendprojects.chessapp.ai.eval.Minimax;
import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;

public class ExpertAI implements AIStrategy {
    private final Minimax minimax = new Minimax(8, Evaluator::combined, true);
    @Override
    public Move chooseMove(Colour colour, Board board) {
        return minimax.getOptimalMove(colour, board);
    }
}
