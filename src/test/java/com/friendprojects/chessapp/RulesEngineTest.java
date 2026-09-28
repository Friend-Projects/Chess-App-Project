package com.friendprojects.chessapp;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;
import com.friendprojects.chessapp.rules.Rules;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RulesEngineTest extends BaseTest {

    @Test
    public void testKingNotInCheck() {
        Board board = new Board();
        board.setupBoard();

        boolean checkStatusWhite = Rules.RULES_ENGINE.isKingInCheck(Colour.WHITE, board);
        boolean checkStatusBlack = Rules.RULES_ENGINE.isKingInCheck(Colour.BLACK, board);

        assertFalse(checkStatusWhite);
        assertFalse(checkStatusBlack);
    }

    @Test
    public void testKingInCheck() {
        Board board = new Board();
        Piece whiteKing = new Piece(PieceType.KING, Colour.WHITE);
        Piece blackBishop = new Piece(PieceType.BISHOP, Colour.BLACK);
        board.setKing(whiteKing);

        board.addPieceAt(new Position(4, 0), whiteKing);
        board.addPieceAt(new Position(1, 3), blackBishop);

        boolean checkStatusWhite = Rules.RULES_ENGINE.isKingInCheck(Colour.WHITE, board);
        assertTrue(checkStatusWhite);
    }

    @Test
    public void testPieceLegalMoves() {
        Board board = new Board();
        Piece whiteKing = new Piece(PieceType.KING, Colour.WHITE);
        Piece whitePawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece blackBishop = new Piece(PieceType.BISHOP, Colour.BLACK);
        Piece blackQueen = new Piece(PieceType.QUEEN, Colour.BLACK);
        board.setKing(whiteKing);

        board.addPieceAt(new Position(4, 0), whiteKing);
        board.addPieceAt(new Position(4, 1), whitePawn);
        board.addPieceAt(new Position(1, 3), blackBishop);
        board.addPieceAt(new Position(7, 4), blackQueen);
        List<Move> kingMoves = Rules.RULES_ENGINE.getLegalMovesForPiece(whiteKing, board);
        List<Move> pawnMoves = Rules.RULES_ENGINE.getLegalMovesForPiece(whitePawn, board);

        assertFalse(kingMoves.isEmpty());
        assertTrue(pawnMoves.isEmpty());
    }

    @Test
    public void testGetPieceLegalMoves() {
        Board board = new Board();
        Piece whiteKing = new Piece(PieceType.KING, Colour.WHITE);
        Piece whiteRook = new Piece(PieceType.ROOK, Colour.WHITE);
        Piece blackRook = new Piece(PieceType.ROOK, Colour.BLACK);
        Piece blackBishop = new Piece(PieceType.BISHOP, Colour.BLACK);
        board.setKing(whiteKing);

        board.addPieceAt(new Position(4, 0), whiteKing);
        board.addPieceAt(new Position(4, 1), whiteRook);
        board.addPieceAt(new Position(4, 7), blackRook);
        board.addPieceAt(new Position(0, 3), blackBishop);
        List<Move> rookLegalMoves = Rules.RULES_ENGINE.getLegalMovesForPiece(whiteRook, board);
        List<Move> kingLegalMoves = Rules.RULES_ENGINE.getLegalMovesForPiece(whiteKing, board);

        assertEquals(6, rookLegalMoves.size());
        assertEquals(3, kingLegalMoves.size());
    }

    @Test
    public void testIsMoveLegal() {
        Board board = new Board();
        Piece whiteKing = new Piece(PieceType.KING, Colour.WHITE);
        Piece blackRook = new Piece(PieceType.ROOK, Colour.BLACK, false);

        board.setKing(whiteKing);
        board.addPieceAt(new Position(4, 0), whiteKing);
        board.addPieceAt(new Position(4, 7), blackRook);

        Move legalMove = new Move(whiteKing, new Position(4, 0), new Position(3, 1));
        Move illegalMove = new Move(whiteKing, new Position(4, 0), new Position(4, 1));
        List<Move> validMoves = Rules.MOVE_VALIDATOR.getValidMoves(whiteKing, board);

        assertTrue(validMoves.contains(illegalMove));
        assertTrue(Rules.RULES_ENGINE.isMoveLegal(legalMove, board));
        assertFalse(Rules.RULES_ENGINE.isMoveLegal(illegalMove, board));
    }
}
