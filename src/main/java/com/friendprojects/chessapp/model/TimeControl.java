package com.friendprojects.chessapp.model;

public class TimeControl {
    private final int duration;
    private final int increment;

    public TimeControl(int duration, int increment) {
        this.duration = duration; // Seconds
        this.increment = increment; // Seconds
    }

    public int getDuration() {
        return this.duration;
    }

    public int getIncrement() {
        return this.increment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("Duration: ").append(this.duration).append("\t| ");
        sb.append("Increment: ").append(this.increment);
        sb.append("]");
        return sb.toString();
    }
}
