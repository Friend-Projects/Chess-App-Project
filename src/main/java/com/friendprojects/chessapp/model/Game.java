package com.friendprojects.chessapp.model;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.GameState;
import com.friendprojects.chessapp.enums.PieceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final Board board;
    private final GameSetting gameSetting;
    private final List<Move> moveHistory;

    private final Player whitePlayer;
    private final Player blackPlayer;

    private Player currentTurn;
    private GameState gameState;
    private int halfMoveClock;
    private int fullMoveClock;

    public Game(Board board, GameSetting gameSetting, Player whitePlayer, Player blackPlayer) {
        this.board = board;
        this.gameSetting = gameSetting;
        this.moveHistory = new ArrayList<>();
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentTurn = whitePlayer;
        this.gameState = GameState.ACTIVE;
    }

    public Board getBoard() {
        return this.board;
    }

    public GameSetting getGameSetting() {
        return this.gameSetting;
    }

    public Move getLastMove() {
        return this.moveHistory.get(this.moveHistory.size() - 1);
    }

    public List<Move> getMoveHistory() {
        return Collections.unmodifiableList(this.moveHistory);
    }

    public Player getWhitePlayer() {
        return this.whitePlayer;
    }

    public Player getBlackPlayer() {
        return this.blackPlayer;
    }

    public Player getCurrentTurn() {
        return this.currentTurn;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public int getHalfMoveClock() {
        return this.halfMoveClock;
    }

    public int getFullMoveClock() {
        return this.fullMoveClock;
    }

    public void appendToMoveHistory(Move move) {
        this.moveHistory.add(move);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void applyMove(Move move) {
        this.board.executeMove(move);
        if (move.getPiece().getType() == PieceType.PAWN || move.getCapturedPiece() != null) {
            this.halfMoveClock = 0;
        } else {
            this.halfMoveClock++;
        }
        if (this.currentTurn == this.blackPlayer) {
            this.fullMoveClock++;
        }
        this.currentTurn = this.currentTurn.getColor() == Colour.WHITE ? this.blackPlayer : this.whitePlayer;
    }
}
