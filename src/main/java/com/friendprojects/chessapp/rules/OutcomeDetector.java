package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.model.Game;
import com.friendprojects.chessapp.model.Player;

public class OutcomeDetector {

    private final RulesEngine rulesEngine;

    public OutcomeDetector(RulesEngine rulesEngine) {
        this.rulesEngine = rulesEngine;
    }

    public boolean isCheckmate(Player player, Game game) {
        return false;
    }

    public boolean isDrawByStalemate(Player player, Game game) {
        return false;
    }

    public boolean isDrawByRepetition(Game game) {
        return false;
    }

    public boolean isDrawBy50MoveRule(Game game) {
        return false;
    }

    public boolean isDrawByInsufficientMaterial(Game game) {
        return false;
    }
}
