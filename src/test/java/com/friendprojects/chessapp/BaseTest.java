package com.friendprojects.chessapp;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseTest {

    protected static final Map<String, Board> BOARDS = new HashMap<>();

    @BeforeAll
    public static void setUp() {
        initialiseBoard_1();
        initialiseBoard_2();
        initialiseBoard_3();
        initialiseBoard_4();
        initialiseBoard_5();
    }

    private static void initialiseBoard_1() {
        Board board = new Board();
        board.setupBoard();
        BOARDS.put("Default_Starting_Game_Board", board);
    }

    private static void initialiseBoard_2() {
        Board board = new Board();
        BOARDS.put("Empty_Board", board);
    }

    private static void initialiseBoard_3() {
        Board board = new Board();
        Piece pawn1 = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece pawn2 = new Piece(PieceType.PAWN, Colour.BLACK);
        Piece pawn3 = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece pawn4 = new Piece(PieceType.PAWN, Colour.BLACK);
        Piece pawn5 = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece knight1 = new Piece(PieceType.KNIGHT, Colour.BLACK);
        Piece knight2 = new Piece(PieceType.KNIGHT, Colour.BLACK);
        board.addPieceAt(new Position(0, 1), pawn1);
        board.addPieceAt(new Position(1, 6), pawn2);
        board.addPieceAt(new Position(5, 3), pawn3);
        board.addPieceAt(new Position(6, 6), pawn4);
        board.addPieceAt(new Position(3, 6), pawn5);
        board.addPieceAt(new Position(4, 4), knight1);
        board.addPieceAt(new Position(2, 7), knight2);

        Move move = new Move(pawn3, new Position(5, 3), new Position(5, 4));
        Move move2 = new Move(pawn4, new Position(6, 6), new Position(6, 4));
        board.executeMove(move);
        board.executeMove(move2);
        board.setEnPassantCapture(pawn4);
        BOARDS.put("Pawns_Board", board);
    }

    private static void initialiseBoard_4() {
        Board board = new Board();
        Piece knight1 = new Piece(PieceType.KNIGHT, Colour.WHITE);
        Piece knight2 = new Piece(PieceType.KNIGHT, Colour.WHITE);
        Piece knight3 = new Piece(PieceType.KNIGHT, Colour.BLACK);
        Piece knight4 = new Piece(PieceType.KNIGHT, Colour.BLACK);
        board.addPieceAt(new Position(2, 2), knight1);
        board.addPieceAt(new Position(3, 4), knight2);
        board.addPieceAt(new Position(1, 4), knight3);
        board.addPieceAt(new Position(7, 7), knight4);
        BOARDS.put("Knights_Board", board);
    }

    private static void initialiseBoard_5() {
        Board board = new Board();
        BOARDS.put("Bishops_Board", board);
    }
}
