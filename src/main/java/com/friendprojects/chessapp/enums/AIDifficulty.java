package com.friendprojects.chessapp.enums;

public enum AIDifficulty {
    NOOB, // 0-ply Random select with capture prioritisation
    EASY, // 1-ply Greedy search via material evaluation with occasional random select
    MEDIUM, // 2-ply Minimax via material evaluation
    HARD, // 2-ply Minimax via position & material evaluation
    EXPERT, // 4-ply Minimax via position & material evaluation with alpha-beta pruning
    IMPOSSIBLE; // Stockfish engine
}
