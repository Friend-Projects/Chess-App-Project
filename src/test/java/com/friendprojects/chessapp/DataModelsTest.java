package com.friendprojects.chessapp;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataModelsTest {

    @Nested
    class PositionTest {

        @Test
        public void testInvalidCreation() {
            assertThrows(IllegalArgumentException.class, () -> new Position(-1, -3));
            assertThrows(IllegalArgumentException.class, () -> new Position(-2, 9));
            assertThrows(IllegalArgumentException.class, () -> new Position(1, -3));
            assertThrows(IllegalArgumentException.class, () -> new Position(10, 8));
        }

        @Test
        public void testAlgebraicConversion() {
            Position pos = new Position(4, 3);
            assertEquals("e4", pos.toAlgebraic());
        }

        @Test
        public void testPositionConversion() {
            Position expected = new Position(7, 7);
            assertEquals(expected, Position.toCoords("h8"));
        }

        @Test
        public void testEquality() {
            Position one = new Position(2, 3);
            Position two = new Position(2, 3);
            assertEquals(one, two);
        }

        @Test
        public void testValidOffset() {
            Position one = new Position(1, 1);
            Position two = new Position(2, 7);
            assertEquals(two, one.offset(1, 6));
        }

        @Test
        public void testInvalidOffset() {
            Position one = new Position(4, 4);
            assertNull(one.offset(4, 4));
        }
    }

    @Nested
    class PieceTest {

        @Test
        public void testEquality() {
            Piece one = new Piece(PieceType.KNIGHT, Colour.BLACK, new Position(1, 2));
            Piece two = new Piece(PieceType.KNIGHT, Colour.BLACK, new Position(1, 2));
            assertEquals(one, two);
        }

        @Test
        public void testInequality() {
            Piece one = new Piece(PieceType.QUEEN, Colour.WHITE, new Position(7, 7));
            Piece two = new Piece(PieceType.BISHOP, Colour.BLACK, new Position(0, 0));
            assertNotEquals(one, two);
        }
    }

    @Nested
    class MoveTest {

        @Test
        public void testEquality() {
            Piece piece1 = new Piece(PieceType.PAWN, Colour.BLACK, new Position(1, 6));
            Move move1 = new Move(piece1, new Position(1, 6), new Position(1, 7), PieceType.QUEEN);
            Piece piece2 = new Piece(PieceType.PAWN, Colour.BLACK, new Position(1, 6));
            Move move2 = new Move(piece2, new Position(1, 6), new Position(1, 7), PieceType.QUEEN);
            assertEquals(move1, move2);
        }

        @Test
        public void testInequality() {
            Piece piece1 = new Piece(PieceType.KNIGHT, Colour.WHITE, new Position(3, 4));
            Move move1 = new Move(piece1, new Position(3, 4), new Position(2, 6));
            Piece piece2 = new Piece(PieceType.KNIGHT, Colour.WHITE, new Position(3, 5));
            Move move2 = new Move(piece2, new Position(3, 5), new Position(2, 7));
            assertNotEquals(move1, move2);
        }
    }

    @Nested
    class BoardTest {

        @Test
        public void testMoveExecution() {
            Board board = new Board();
            board.setupBoard();
            Piece piece = board.getPieceAt(new Position(1, 1));
            Move move = new Move(piece, new Position(1, 1), new Position(1, 3));
            board.executeMove(move);
            assertNull(board.getPieceAt(new Position(1, 1)));
            assertEquals(piece, board.getPieceAt(new Position(1, 3)));
        }
    }

    @Nested
    class GameTest {

    }
}
