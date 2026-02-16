package com.friendprojects.chessapp;

import com.friendprojects.chessapp.enums.AIDifficulty;
import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.Display;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager.init(stage);
        SceneManager.loadMainMenu();
        stage.setTitle("Chess App");
        stage.show();

        // Test display of data models
        Position pos = new Position(0, 0);
        Position pos2 = new Position(0, 1);
        Piece piece = new Piece(PieceType.PAWN, Colour.WHITE, pos);
        Move move = new Move(piece, pos, pos2, null, PieceType.QUEEN, null);
        Player player = new HumanPlayer(Colour.WHITE);
        TimeControl tc = new TimeControl(180, 2);
        GameSetting gs = new GameSetting(false, tc, AIDifficulty.EASY);
        Board board = new Board();
        board.setupBoard();
        System.out.println(board.display(Display.CLI));
        System.out.println(pos.equals(pos2) + " ----- " + pos);
        System.out.println(piece);
        System.out.println(move);
        System.out.println(player);
        System.out.println(tc);
        System.out.println(gs);
        System.out.println(board);
    }

    public static void main(String[] args) {
        launch();
    }
}