package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.ai.AIStrategy;
import com.friendprojects.chessapp.enums.Colour;

public class AIPlayer extends Player {

    private final AIStrategy aiStrategy;

    public AIPlayer(Colour colour, AIStrategy aiStrategy) {
        super(colour);
        this.aiStrategy = aiStrategy;
    }

    @Override
    public Move chooseMove(Board board, Game game) {
        return aiStrategy.chooseMove(getColor(), board);
    }
}
