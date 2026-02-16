package com.friendprojects.chessapp.rules;

public class Rules {

    public static final MoveValidator MOVE_VALIDATOR = new MoveValidator();
    public static final RulesEngine RULES_ENGINE = new RulesEngine(MOVE_VALIDATOR);
    public static final OutcomeDetector OUTCOME_DETECTOR = new OutcomeDetector(RULES_ENGINE);
}
