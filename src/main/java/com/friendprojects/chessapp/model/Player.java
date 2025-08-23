package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;

import javax.print.DocFlavor;

public abstract class Player {
    private final Colour colour;

    public Player(Colour colour) {
        this.colour = colour;
    }

    public abstract Move chooseMove(Board board, Game game);

    public Colour getColor() {
        return this.colour;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Colour: ").append(this.colour);
        sb.append("]");
        return sb.toString();
    }
}
