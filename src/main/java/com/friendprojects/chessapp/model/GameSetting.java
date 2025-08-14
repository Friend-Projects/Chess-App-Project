package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.AIDifficulty;

public class GameSetting {
    private final boolean isPvp;
    private final TimeControl timeControl;
    private final AIDifficulty aiDifficulty;

    public GameSetting(boolean isPvp, TimeControl timeControl, AIDifficulty aiDifficulty) {
        this.isPvp = isPvp;
        this.timeControl = timeControl;
        this.aiDifficulty = aiDifficulty;
    }

    public boolean getIsPvp() {
        return this.isPvp;
    }

    public TimeControl getTimeControl() {
        return this.timeControl;
    }

    public AIDifficulty getAiDifficulty() {
        return this.aiDifficulty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("PvP Mode: ").append(this.isPvp).append("\t| ");
        sb.append("Time Control: ").append(this.timeControl).append("\t| ");
        sb.append("AI Difficulty: ").append(this.aiDifficulty);
        sb.append("]");
        return sb.toString();
    }
}
