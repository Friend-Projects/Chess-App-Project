package com.friendprojects.chessapp.model;

public class TimeControl {
    private final int duration;
    private final int increment;

    public TimeControl(int duration, int increment) {
        this.duration = duration;
        this.increment = increment;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getIncrement() {
        return this.increment;
    }
}
