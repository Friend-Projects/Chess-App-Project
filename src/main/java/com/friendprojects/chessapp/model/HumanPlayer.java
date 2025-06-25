package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;

public class HumanPlayer extends Player {

    public HumanPlayer(Colour color) {
        super(color);
    }

    @Override
    public Move chooseMove(Board board, Game game) {
        return null;
    }
}
