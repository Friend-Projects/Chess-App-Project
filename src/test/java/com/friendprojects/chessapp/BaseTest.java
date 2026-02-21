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
        initialiseBoard_Starting();
        initialiseBoard_Empty();
        initialiseBoard_Pawns();
        initialiseBoard_Knights();
        initialiseBoard_Bishops();
        initialiseBoard_Rooks();
        initialiseBoard_Queens();
        initialiseBoard_Kings();

        initialiseBoard_KQvK();
        initialiseBoard_KRvK();
        initialiseBoard_KBNvK();
        initialiseBoard_RooksPawns();
        initialiseBoard_BishopsKnightsPawns();

        initialiseBoard_Italian();
        initialiseBoard_London();
        initialiseBoard_RuyLopez();
        initialisesBoard_Sicilian();
        initialiseBoard_PinsForks_Tactic();
    }

    private static void initialiseBoard_Starting() {
        Board board = new Board();
        board.setupBoard();
        BOARDS.put("Default", board);
    }

    private static void initialiseBoard_Empty() {
        Board board = new Board();
        BOARDS.put("Empty", board);
    }

    private static void initialiseBoard_Pawns() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // White Pawns
        board.addPieceAt(new Position(0, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(3, 6), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(5, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));

        // Black Pawns
        board.addPieceAt(new Position(6, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(1, 6), new Piece(PieceType.PAWN, Colour.BLACK));

        // Capture/Blocking Pieces
        board.addPieceAt(new Position(4, 4), new Piece(PieceType.KNIGHT, Colour.BLACK));
        board.addPieceAt(new Position(2, 7), new Piece(PieceType.KNIGHT, Colour.BLACK));

        BOARDS.put("Pawns", board);
    }

    private static void initialiseBoard_Knights() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // White Knights
        board.addPieceAt(new Position(2, 2), new Piece(PieceType.KNIGHT, Colour.WHITE));
        board.addPieceAt(new Position(3, 4), new Piece(PieceType.KNIGHT, Colour.WHITE));

        // Black Knights
        board.addPieceAt(new Position(1, 4), new Piece(PieceType.KNIGHT, Colour.BLACK));
        board.addPieceAt(new Position(7, 7), new Piece(PieceType.KNIGHT, Colour.BLACK));

        BOARDS.put("Knights", board);
    }

    private static void initialiseBoard_Bishops() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // White Bishops
        board.addPieceAt(new Position(2, 0), new Piece(PieceType.BISHOP, Colour.WHITE));
        board.addPieceAt(new Position(5, 3), new Piece(PieceType.BISHOP, Colour.WHITE));

        // Black Bishops
        board.addPieceAt(new Position(6, 6), new Piece(PieceType.BISHOP, Colour.BLACK));
        board.addPieceAt(new Position(1, 4), new Piece(PieceType.BISHOP, Colour.BLACK));

        // Capture/Blocking Pieces
        board.addPieceAt(new Position(3, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(4, 2), new Piece(PieceType.PAWN, Colour.BLACK, false));
        board.addPieceAt(new Position(4, 4), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(5, 5), new Piece(PieceType.PAWN, Colour.BLACK, false));

        BOARDS.put("Bishops", board);
    }

    private static void initialiseBoard_Rooks() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // White Rooks
        board.addPieceAt(new Position(0, 0), new Piece(PieceType.ROOK, Colour.WHITE));
        board.addPieceAt(new Position(7, 3), new Piece(PieceType.ROOK, Colour.WHITE, false));

        // Black Rooks
        board.addPieceAt(new Position(0, 7), new Piece(PieceType.ROOK, Colour.BLACK));
        board.addPieceAt(new Position(3, 5), new Piece(PieceType.ROOK, Colour.BLACK, false));

        // Capture/Blocking Pieces
        board.addPieceAt(new Position(0, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(4, 3), new Piece(PieceType.PAWN, Colour.BLACK, false));
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));

        BOARDS.put("Rooks", board);
    }

    private static void initialiseBoard_Queens() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Queens
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.QUEEN, Colour.WHITE));
        board.addPieceAt(new Position(5, 5), new Piece(PieceType.QUEEN, Colour.BLACK));

        // Capture/Blocking Pieces
        board.addPieceAt(new Position(3, 6), new Piece(PieceType.PAWN, Colour.BLACK, false));
        board.addPieceAt(new Position(6, 3), new Piece(PieceType.KNIGHT, Colour.BLACK));
        board.addPieceAt(new Position(4, 4), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(2, 4), new Piece(PieceType.BISHOP, Colour.WHITE));

        BOARDS.put("Queens", board);
    }

    private static void initialiseBoard_Kings() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(3, 1), new Piece(PieceType.KING, Colour.WHITE, false));
        board.addPieceAt(new Position(5, 3), new Piece(PieceType.KING, Colour.BLACK, false));

        // Capture/Blocking Pieces
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.ROOK, Colour.WHITE, false));
        board.addPieceAt(new Position(6, 2), new Piece(PieceType.BISHOP, Colour.BLACK));
        board.addPieceAt(new Position(4, 2), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(4, 3), new Piece(PieceType.PAWN, Colour.BLACK, false));

        BOARDS.put("Kings", board);
    }

    private static void initialiseBoard_KQvK() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Queen
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.QUEEN, Colour.WHITE));

        BOARDS.put("KQvK", board);
    }

    private static void initialiseBoard_KRvK() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Rook
        board.addPieceAt(new Position(0, 3), new Piece(PieceType.ROOK, Colour.WHITE, false));

        BOARDS.put("KRvK", board);
    }

    private static void initialiseBoard_KBNvK() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Bishop and Knight
        board.addPieceAt(new Position(2, 2), new Piece(PieceType.BISHOP, Colour.WHITE));
        board.addPieceAt(new Position(6, 3), new Piece(PieceType.KNIGHT, Colour.WHITE));

        BOARDS.put("KBNvK", board);
    }

    private static void initialiseBoard_RooksPawns() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Pawns
        board.addPieceAt(new Position(0, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(1, 2), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(6, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(5, 5), new Piece(PieceType.PAWN, Colour.BLACK, false));

        // Rooks
        board.addPieceAt(new Position(7, 1), new Piece(PieceType.ROOK, Colour.WHITE, false));
        board.addPieceAt(new Position(0, 6), new Piece(PieceType.ROOK, Colour.BLACK, false));

        BOARDS.put("RooksPawns", board);
    }

    private static void initialiseBoard_BishopsKnightsPawns() {
        Board board = new Board();

        // Kings
        board.addPieceAt(new Position(4, 0), new Piece(PieceType.KING, Colour.WHITE));
        board.addPieceAt(new Position(4, 7), new Piece(PieceType.KING, Colour.BLACK));

        // Bishops
        board.addPieceAt(new Position(5, 2), new Piece(PieceType.BISHOP, Colour.WHITE));
        board.addPieceAt(new Position(2, 5), new Piece(PieceType.BISHOP, Colour.BLACK));

        // Knights
        board.addPieceAt(new Position(2, 2), new Piece(PieceType.KNIGHT, Colour.WHITE));
        board.addPieceAt(new Position(6, 5), new Piece(PieceType.KNIGHT, Colour.BLACK));

        // Pawns
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(4, 4), new Piece(PieceType.PAWN, Colour.BLACK, false));

        BOARDS.put("BishopsKnightsPawns", board);
    }

    private static void initialiseBoard_Italian() {
        Board board = new Board();

        board.setupBoard();

        // First five half-moves of an Italian game
        board.executeMove(new Move(board.getPieceAt(new Position(4, 1)), new Position(4, 1), new Position(4, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(4, 6)), new Position(4, 6), new Position(4, 4)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 0)), new Position(6, 0), new Position(5, 2)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 7)), new Position(6, 7), new Position(2, 5)));
        board.executeMove(new Move(board.getPieceAt(new Position(5, 7)), new Position(5, 7), new Position(2, 3)));

        BOARDS.put("Italian_Opening", board);
    }

    private static void initialiseBoard_London() {
        Board board = new Board();

        board.setupBoard();

        // First six half-moves of the London System
        board.executeMove(new Move(board.getPieceAt(new Position(3, 1)), new Position(3, 1), new Position(3, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(3, 6)), new Position(3, 6), new Position(3, 4)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 0)), new Position(6, 0), new Position(5, 2)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 7)), new Position(6, 7), new Position(5, 5)));
        board.executeMove(new Move(board.getPieceAt(new Position(2, 0)), new Position(2, 0), new Position(5, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(2, 6)), new Position(2, 6), new Position(2, 4)));

        BOARDS.put("London_Opening", board);
    }

    private static void initialiseBoard_RuyLopez() {
        Board board = new Board();

        board.setupBoard();

        // First six half-moves of the Ruy Lopez Classical Defense
        board.executeMove(new Move(board.getPieceAt(new Position(4, 1)), new Position(4, 1), new Position(4, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(4, 6)), new Position(4, 6), new Position(4, 4)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 0)), new Position(6, 0), new Position(5, 2)));
        board.executeMove(new Move(board.getPieceAt(new Position(1, 7)), new Position(1, 7), new Position(2, 5)));
        board.executeMove(new Move(board.getPieceAt(new Position(5, 0)), new Position(5, 0), new Position(1, 4)));
        board.executeMove(new Move(board.getPieceAt(new Position(5, 7)), new Position(5, 7), new Position(2, 4)));

        BOARDS.put("Ruy_Lopez_Classical_Defense", board);
    }

    private static void initialisesBoard_Sicilian() {
        Board board = new Board();

        board.setupBoard();

        // First ten half-moves of the Sicilian Defense Najdorf Variation
        board.executeMove(new Move(board.getPieceAt(new Position(4, 1)), new Position(4, 1), new Position(4, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(2, 6)), new Position(2, 6), new Position(2, 4)));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 0)), new Position(6, 0), new Position(5, 2)));
        board.executeMove(new Move(board.getPieceAt(new Position(3, 6)), new Position(3, 6), new Position(3, 5)));
        board.executeMove(new Move(board.getPieceAt(new Position(3, 1)), new Position(3, 1), new Position(3, 3)));
        board.executeMove(new Move(board.getPieceAt(new Position(2, 4)), new Position(2, 4), new Position(3, 3), board.getPieceAt(new Position(3, 3))));
        board.executeMove(new Move(board.getPieceAt(new Position(5, 2)), new Position(5, 2), new Position(3, 3), board.getPieceAt(new Position(3, 3))));
        board.executeMove(new Move(board.getPieceAt(new Position(6, 7)), new Position(6, 7), new Position(5, 5)));
        board.executeMove(new Move(board.getPieceAt(new Position(1, 0)), new Position(1, 0), new Position(2, 2)));
        board.executeMove(new Move(board.getPieceAt(new Position(0, 6)), new Position(0, 6), new Position(0, 5)));

        BOARDS.put("Sicilian_Najdorf_Variation", board);
    }

    private static void initialiseBoard_PinsForks_Tactic() {
        Board board = new Board();

        // White pieces
        board.addPieceAt(new Position(6, 0), new Piece(PieceType.KING, Colour.WHITE, false));
        board.addPieceAt(new Position(5, 0), new Piece(PieceType.ROOK, Colour.WHITE, false));
        board.addPieceAt(new Position(0, 0), new Piece(PieceType.ROOK, Colour.WHITE));
        board.addPieceAt(new Position(3, 1), new Piece(PieceType.QUEEN,  Colour.WHITE));
        board.addPieceAt(new Position(2, 2), new Piece(PieceType.KNIGHT, Colour.WHITE));
        board.addPieceAt(new Position(5, 2), new Piece(PieceType.KNIGHT, Colour.WHITE));
        board.addPieceAt(new Position(2, 3), new Piece(PieceType.BISHOP, Colour.WHITE));
        board.addPieceAt(new Position(6, 3), new Piece(PieceType.BISHOP, Colour.WHITE));
        board.addPieceAt(new Position(6, 4), new Piece(PieceType.BISHOP, Colour.WHITE));

        // White pawns
        board.addPieceAt(new Position(0, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(1, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(2, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(3, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(4, 3), new Piece(PieceType.PAWN, Colour.WHITE, false));
        board.addPieceAt(new Position(5, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(6, 1), new Piece(PieceType.PAWN, Colour.WHITE));
        board.addPieceAt(new Position(7, 1), new Piece(PieceType.PAWN, Colour.WHITE));

        // Black pieces
        board.addPieceAt(new Position(6, 7), new Piece(PieceType.KING, Colour.BLACK, false));
        board.addPieceAt(new Position(5, 7), new Piece(PieceType.ROOK, Colour.BLACK, false));
        board.addPieceAt(new Position(0, 7), new Piece(PieceType.ROOK, Colour.BLACK));
        board.addPieceAt(new Position(3, 7), new Piece(PieceType.QUEEN,  Colour.BLACK));
        board.addPieceAt(new Position(2, 5), new Piece(PieceType.KNIGHT, Colour.BLACK));
        board.addPieceAt(new Position(5, 5), new Piece(PieceType.KNIGHT, Colour.BLACK));
        board.addPieceAt(new Position(2, 4), new Piece(PieceType.BISHOP, Colour.BLACK));
        board.addPieceAt(new Position(4, 6), new Piece(PieceType.BISHOP, Colour.BLACK));

        // Black pawns
        board.addPieceAt(new Position(0, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(1, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(2, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(3, 5), new Piece(PieceType.PAWN, Colour.BLACK, false));
        board.addPieceAt(new Position(4, 4), new Piece(PieceType.PAWN, Colour.BLACK, false));
        board.addPieceAt(new Position(5, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(6, 6), new Piece(PieceType.PAWN, Colour.BLACK));
        board.addPieceAt(new Position(7, 6), new Piece(PieceType.PAWN, Colour.BLACK));

        BOARDS.put("PinsForks_Tactic", board);
    }
}
