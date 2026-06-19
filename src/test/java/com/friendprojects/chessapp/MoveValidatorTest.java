package com.friendprojects.chessapp;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;
import com.friendprojects.chessapp.rules.Rules;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoveValidatorTest extends BaseTest {

    @Test
    public void testPawnForward() {
        Board board = new Board();
        Piece pawn = new Piece(PieceType.PAWN, Colour.WHITE);
        board.addPieceAt(new Position(4, 1), pawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 1), board);
        assertEquals(2, moves.size());
        assertEquals(positionsInCoords("e3", "e4"), targetPositions(moves));
    }

    @Test
    public void testPawnObstructed() {
        Board board = new Board();
        Piece pawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.BLACK, false);
        board.addPieceAt(new Position(4, 1), pawn);
        board.addPieceAt(new Position(4, 2), blockPawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 1), board);
        assertEquals(0, moves.size());
    }

    @Test
    public void testPawnCapture() {
        Board board = new Board();
        Piece pawn = new Piece(PieceType.PAWN, Colour.WHITE, false);
        Piece knight = new Piece(PieceType.KNIGHT, Colour.BLACK, false);
        board.addPieceAt(new Position(4, 3), pawn);
        board.addPieceAt(new Position(5, 4), knight);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 3), board);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Move(pawn, new Position(4, 3), new Position(5, 4), knight)));
    }

    @Test
    public void testPawnEnPassant() {
        Board board = new Board();
        Piece pawn = new Piece(PieceType.PAWN, Colour.WHITE, false);
        Piece capturePawn = new Piece(PieceType.PAWN, Colour.BLACK, false);
        board.addPieceAt(new Position(4, 4), pawn);
        board.addPieceAt(new Position(3, 4), capturePawn);
        board.setEnPassantCapture(capturePawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 4), board);
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new Move(pawn, new Position(4, 4), new Position(3, 5), capturePawn)));
    }

    @Test
    public void testKnightMove() {
        Board board = new Board();
        Piece knight = new Piece(PieceType.KNIGHT, Colour.WHITE);
        board.addPieceAt(new Position(1, 0), knight);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(1, 0), board);
        assertEquals(3, moves.size());
        assertEquals(positionsInCoords("d2", "a3", "c3"), targetPositions(moves));
    }

    @Test
    public void testKnightObstructed() {
        Board board = new Board();
        Piece knight = new Piece(PieceType.KNIGHT, Colour.BLACK);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.BLACK);
        board.addPieceAt(new Position(6, 7), knight);
        board.addPieceAt(new Position(5, 5), blockPawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(6, 7), board);
        assertEquals(2, moves.size());
        assertEquals(positionsInCoords("e7", "h6"), targetPositions(moves));
    }

    @Test
    public void testKnightCapture() {
        Board board = new Board();
        Piece knight = new Piece(PieceType.KNIGHT, Colour.WHITE);
        Piece capturePawn = new Piece(PieceType.PAWN, Colour.BLACK);
        board.addPieceAt(new Position(1, 0), knight);
        board.addPieceAt(new Position(3, 1), capturePawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(1, 0), board);
        assertEquals(3, moves.size());
        assertTrue(moves.contains(new Move(knight, new Position(1, 0), new Position(3, 1), capturePawn)));
    }

    @Test
    public void testBishopMovesDiagonal() {
        Board board = new Board();
        Piece bishop = new Piece(PieceType.BISHOP, Colour.WHITE);
        board.addPieceAt(new Position(2, 0), bishop);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(2, 0), board);
        assertEquals(7, moves.size());
        assertEquals(positionsInCoords("a3", "b2", "d2", "e3", "f4", "g5", "h6"), targetPositions(moves));
    }

    @Test
    public void testBishopObstructed() {
        Board board = new Board();
        Piece bishop = new Piece(PieceType.BISHOP, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece blockPawn2 = new Piece(blockPawn);
        board.addPieceAt(new Position(4, 4), bishop);
        board.addPieceAt(new Position(1, 1), blockPawn);
        board.addPieceAt(new Position(2, 6), blockPawn2);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 4), board);
        assertEquals(9, moves.size());
        assertEquals(positionsInCoords("c3", "d4", "d6", "f6", "g7", "h8", "f4", "g3", "h2"), targetPositions(moves));
    }

    @Test
    public void testBishopCapture() {
        Board board = new Board();
        Piece bishop = new Piece(PieceType.BISHOP, Colour.WHITE);
        Piece capturePawn = new Piece(PieceType.PAWN, Colour.BLACK);
        board.addPieceAt(new Position(2, 3), bishop);
        board.addPieceAt(new Position(5, 6), capturePawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(2, 3), board);
        assertEquals(10, moves.size());
        assertTrue(moves.contains(new Move(bishop, new Position(2, 3), new Position(5, 6), capturePawn)));
    }

    @Test
    public void testRookMovesOrthogonal() {
        Board board = new Board();
        Piece rook = new Piece(PieceType.ROOK, Colour.BLACK);
        board.addPieceAt(new Position(4, 5), rook);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 5), board);
        assertEquals(14, moves.size());
        assertEquals(positionsInCoords("a6", "b6", "c6", "d6", "e1", "e2", "e3", "e4", "e5", "e7", "e8", "f6", "g6", "h6"), targetPositions(moves));
    }

    @Test
    public void testRookObstructed() {
        Board board = new Board();
        Piece rook = new Piece(PieceType.ROOK, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.WHITE);
        board.addPieceAt(new Position(0, 0), rook);
        board.addPieceAt(new Position(0, 1), blockPawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(0, 0), board);
        assertEquals(7, moves.size());
        assertEquals(positionsInCoords("b1", "c1", "d1", "e1", "f1", "g1", "h1"), targetPositions(moves));
    }

    @Test
    public void testRookCapture() {
        Board board = new Board();
        Piece rook = new Piece(PieceType.ROOK, Colour.BLACK);
        Piece capturePawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece captureKnight = new Piece(PieceType.KNIGHT, Colour.WHITE);
        board.addPieceAt(new Position(7, 7), rook);
        board.addPieceAt(new Position(7, 1), capturePawn);
        board.addPieceAt(new Position(6, 7), captureKnight);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(7, 7), board);
        assertEquals(7, moves.size());
        assertTrue(moves.contains(new Move(rook, new Position(7, 7), new Position(6, 7), captureKnight)));
        assertTrue(moves.contains(new Move(rook, new Position(7, 7), new Position(7, 1), capturePawn)));
    }

    @Test
    public void testQueenMovesOctilinear() {
        Board board = new Board();
        Piece queen = new Piece(PieceType.QUEEN, Colour.WHITE);
        board.addPieceAt(new Position(3, 0), queen);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(3, 0), board);
        assertEquals(21, moves.size());
        assertEquals(positionsInCoords("a1", "b1", "c1", "e1", "f1", "g1", "h1", "a4", "b3", "c2", "d2", "d3", "d4", "d5", "d6", "d7", "d8", "e2", "f3", "g4", "h5"), targetPositions(moves));
    }

    @Test
    public void testQueenObstructed() {
        Board board = new Board();
        Piece queen = new Piece(PieceType.QUEEN, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece blockKnight = new Piece(PieceType.KNIGHT, Colour.WHITE);
        board.addPieceAt(new Position(1, 0), queen);
        board.addPieceAt(new Position(1, 1), blockPawn);
        board.addPieceAt(new Position(2, 0), blockKnight);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(1, 0), board);
        assertEquals(8, moves.size());
        assertEquals(positionsInCoords("a1", "a2", "c2", "d3", "e4", "f5", "g6", "h7"), targetPositions(moves));
    }

    @Test
    public void testQueenCapture() {
        Board board = new Board();
        Piece queen = new Piece(PieceType.QUEEN, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece blockPawn2 = new Piece(PieceType.PAWN, Colour.WHITE);
        Piece capturePawn = new Piece(PieceType.PAWN, Colour.BLACK);
        board.addPieceAt(new Position(3, 0), queen);
        board.addPieceAt(new Position(2, 1), blockPawn);
        board.addPieceAt(new Position(4, 1), blockPawn2);
        board.addPieceAt(new Position(3, 6), capturePawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(3, 0), board);
        assertEquals(13, moves.size());
        assertEquals(positionsInCoords("a1", "b1", "c1", "e1", "f1", "g1", "h1", "d2", "d3", "d4", "d5", "d6", "d7"), targetPositions(moves));
        assertTrue(moves.contains(new Move(queen, new Position(3, 0), new Position(3, 6), capturePawn)));
    }

    @Test
    public void testKingMove() {
        Board board = new Board();
        Piece king = new Piece(PieceType.KING, Colour.WHITE);
        board.addPieceAt(new Position(4,  0), king);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 0), board);
        assertEquals(5, moves.size());
        assertEquals(positionsInCoords("d1", "d2", "e2", "f2", "f1"), targetPositions(moves));
    }

    @Test
    public void testKingObstructed() {
        Board board = new Board();
        Piece king = new Piece(PieceType.KING, Colour.WHITE);
        Piece blockPawn = new Piece(PieceType.KING, Colour.WHITE);
        board.addPieceAt(new Position(4, 0), king);
        board.addPieceAt(new Position(3, 1), blockPawn);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(4, 0), board);
        assertEquals(4, moves.size());
        assertEquals(positionsInCoords("d1", "e2", "f2", "f1"), targetPositions(moves));
    }

    @Test
    public void testKingCapture() {
        Board board = new Board();
        Piece king = new Piece(PieceType.KING, Colour.WHITE);
        Piece captureKnight = new Piece(PieceType.KNIGHT, Colour.BLACK);
        board.addPieceAt(new Position(3, 4), king);
        board.addPieceAt(new Position(4, 4), captureKnight);
        List<Move> moves = Rules.MOVE_VALIDATOR.getValidMoves(board.getPieceAt(3, 4), board);
        assertEquals(8, moves.size());
        assertTrue(moves.contains(new Move(king, new Position(3, 4), new Position(4, 4), captureKnight)));
    }

    private Set<Position> targetPositions(List<Move> moves) {
        return moves.stream().map(Move::getTarget).collect(Collectors.toSet());
    }

    private Set<Position> positionsInCoords(String... squares) {
        return Arrays.stream(squares).map(Position::toCoords).collect(Collectors.toSet());
    }
}
