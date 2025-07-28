package com.friendprojects.chessapp.enums;

public enum Colour {
    WHITE,
    BLACK;

    public Colour opposite() {
        return this == Colour.WHITE ? Colour.BLACK : Colour.WHITE;
    }
}
