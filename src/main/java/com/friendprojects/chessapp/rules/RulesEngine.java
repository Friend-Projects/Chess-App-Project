package com.friendprojects.chessapp.rules;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RulesEngine {

    private final MoveValidator moveValidator;

    public RulesEngine(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    /**
     * Obtain all available legal moves on the board for a specific side.
     *
     * @param colour the piece colour of the player
     * @param board the board being queried
     * @return the list of all legals moves for a piece colour
     */
    public List<Move> getLegalMoves(Colour colour, Board board) {
        List<Move> legalMoves = new ArrayList<>();
        for (Map.Entry<Position, Piece> entry : board.getChessBoard().entrySet()) {
            if (colour == entry.getValue().getColour()) {
                legalMoves.addAll(getLegalMovesForPiece(entry.getValue(), board));
            }
        }
        return legalMoves;
    }

    /**
     * Obtain all available legal moves for a specific piece on the board.
     *
     * @param piece the piece being queried
     * @param board the board that the piece is on
     * @return the list of legal moves for the piece on the board
     */
    public List<Move> getLegalMovesForPiece(Piece piece, Board board) {
        List<Move> pieceLegalMoves = new ArrayList<>();
        List<Move> validMoves = moveValidator.getValidMoves(piece, board);
        for (Move validMove : validMoves) {
            Board dummyBoard = new Board(board);
            dummyBoard.executeMove(validMove);
            if (isKingInCheck(piece.getColour(), dummyBoard)) {
                pieceLegalMoves.add(validMove);
            }
        }
        return pieceLegalMoves;
    }

    public boolean isMoveLegal(Move move, Board board) {
        return getLegalMovesForPiece(move.getPiece(), board).contains(move);
    }

    /**
     * Identify if the king is in check from the current state of the board.
     *
     * @param colour the piece colour of the king
     * @param board the board being queried
     * @return {@code true} if king is in check
     */
    public boolean isKingInCheck(Colour colour, Board board) {
        Piece king = board.getKing(colour);
        int[][] kOffset = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        int[][] dOffset = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] offset : kOffset) {
            Position check = king.getPosition().offset(offset[0], offset[1]);
            Piece checker = board.getPieceAt(check);
            if (check != null && checker.getColour() != colour && checker.getType() == PieceType.KNIGHT) {
                return true;
            }
        }

        for (int[] offset : dOffset) {
            Position check = king.getPosition().offset(offset[0], offset[1]);
            while (check != null) {
                if (board.isOccupied(check)) {
                    Piece checker = board.getPieceAt(check);
                    if (checker.getColour() != colour) {
                        boolean isDiagonal = Math.abs(offset[0]) - Math.abs(offset[1]) == 0;
                        boolean isStraight = (offset)[0] == 0 || offset[1] == 0;
                        if (isDiagonal && (checker.getType() == PieceType.BISHOP || checker.getType() == PieceType.QUEEN)) {
                            return true;
                        } else if (isStraight && (checker.getType() == PieceType.ROOK || checker.getType() == PieceType.QUEEN)) {
                            return true;
                        }
                    }
                    break;
                }
                check = check.offset(offset[0], offset[1]);
            }
        }
        return false;
    }
}
