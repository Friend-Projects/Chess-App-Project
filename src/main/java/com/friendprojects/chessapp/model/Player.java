package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;

public abstract class Player {
    private final Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public abstract Move chooseMove(Board board, Game game);

    public Colour getColor() {
        return this.colour;
    }
}
