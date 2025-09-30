package com.friendprojects.chessapp.enums;

public enum AIDifficulty {
    NOOB, // 0-ply Random Select with capture prioritisation
    EASY, // 1-ply Weighted Random Select via material evaluation
    MEDIUM, // 2-ply Minimax via material evaluation
    HARD, // 4-ply Minimax via position & material evaluation
    EXPERT, // 8-ply Minimax via position & material evaluation with alpha-beta pruning
    IMPOSSIBLE; // Stockfish engine
}
