package com.friendprojects.chessapp.rules;

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

    public List<Move> getLegalMoves(Player player, Game game) {
        List<Move> moves = new ArrayList<>();
        Board board = game.getBoard();
        for (Map.Entry<Position, Piece> entry : board.getChessBoard().entrySet()) {
            List<Move> validPieceMoves = moveValidator.getValidMoves(entry.getValue(), board);

        }
        return moves;
    }

    public List<Move> getLegalMovesForPiece(Piece piece, Game game) {
        return null;
    }

    public boolean isMoveLegal(Move move, Game game) {
        return false;
    }

    public boolean isKingInCheck(Player player, Board board) {
        Piece king = board.getKing(player.getColor());
        int[][] kOffset = new int[][]{{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        int[][] dOffset = new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int[] offset : kOffset) {
            Position check = king.getPosition().offset(offset[0], offset[1]);
            if (check != null && board.isOccupiedByColour(check, player.getColor().opposite()) && board.getPieceAt(check).getType() == PieceType.KNIGHT) {
                return true;
            }
        }
        for (int[] offset : dOffset) {
            Position check = king.getPosition().offset(offset[0], offset[1]);
            while (check != null) {
                if (board.isOccupied(check)) {
                    Piece checker = board.getPieceAt(check);
                    if (checker.getColour() != player.getColor()) {
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
