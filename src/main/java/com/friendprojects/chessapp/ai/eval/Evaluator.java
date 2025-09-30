package com.friendprojects.chessapp.ai.eval;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;

import java.util.*;

import static com.friendprojects.chessapp.rules.Rules.MOVE_VALIDATOR;

public class Evaluator {

    public static final int PHASE_TOTAL = 24;
    private static final EnumMap<PieceType, Integer> ATTACK_VALUES = new EnumMap<>(PieceType.class);
    private static final int[] ATTACKER_WEIGHTS = {0, 50, 75, 88, 94, 97, 99};

    static {
        ATTACK_VALUES.put(PieceType.KNIGHT, 20);
        ATTACK_VALUES.put(PieceType.BISHOP, 20);
        ATTACK_VALUES.put(PieceType.ROOK, 40);
        ATTACK_VALUES.put(PieceType.QUEEN, 80);
    }

    public static int material(Board board) {
        int value = 0;
        for (Piece piece : board.getChessBoard().values()) {
            // Black is negative and white is positive in conjunction to standard eval bar metrics
            value += piece.getColour() == Colour.WHITE ? piece.getMaterialValue() : -piece.getMaterialValue();
        }
        return value / 100;
    }

    public static int position(Board board) {
        int value = 0;
        // Piece-Square Tables (Simplified Evaluation Function)
        for (Piece piece : board.getChessBoard().values()) {
            if (piece.getType() == PieceType.KING) {
                value += PieceSquareTables.getPSTSquare(piece.getType(), piece.getPosition(), piece.getColour(), computePhase(board));
            } else {
                value += PieceSquareTables.getPSTSquare(piece.getType(), piece.getPosition(), piece.getColour());
            }
        }

        // King Safety (Pawn Shield and Attacking King Zone) & Mobility (Count Valid Moves Per Piece and Scale via Piece Type in Centipawns)
        for (Colour colour : Colour.values()) {
            int sign = colour == Colour.WHITE ? 1 : -1;
            value += sign * pawnShield(colour, board);
            value += sign * attackingKingZone(colour, board);
            value += sign * pieceMobility(colour, board);
        }
        return value / 100;
    }

    public static int combined(Board board) {
        return material(board) + position(board);
    }

    private static int computePhase(Board board) {
        // Pawn = 0, Knight = 1, Bishop = 1, Rook = 2, Queen = 4, King = 0
        return board.getPieceCount(PieceType.KNIGHT) + board.getPieceCount(PieceType.BISHOP) + board.getPieceCount(PieceType.ROOK) * 2 + board.getPieceCount(PieceType.QUEEN) * 4;
    }

    private static int pawnShield(Colour colour, Board board) {
        int value = 0;
        Piece king = board.getKing(colour);
        int forward = colour == Colour.WHITE ? 1 : -1;

        for (int colOffset = -1; colOffset < 2; colOffset++) {
            int pawnCol = king.getPosition().getCol() + colOffset;
            if (pawnCol < 0 || pawnCol > 7) continue;

            Piece forwardPiece = board.getPieceAt(new Position(pawnCol, king.getPosition().getRow() + forward));
            if (forwardPiece != null && forwardPiece.getType() == PieceType.PAWN && forwardPiece.getColour() == colour) {
                value += 20;
            } else {
                value -= 15;
            }
        }
        return value;
    }

    private static int attackingKingZone(Colour colour, Board board) {
        Piece king = board.getKing(colour);
        Set<Position> kingZone = getKingZone(king);
        Set<Piece> attackingPieces = new HashSet<>();
        int attackingValue = 0;

        for (Piece piece : board.getChessBoard().values()) {
            if (piece.getColour() == colour) {
                List<Position> attackingSquares = MOVE_VALIDATOR.getValidMoves(piece, board).stream().map(Move::getTarget).toList();

                for (Position square : attackingSquares) {
                    if (kingZone.contains(square)) {
                        attackingPieces.add(piece);
                        attackingValue += ATTACK_VALUES.get(piece.getType());
                    }
                }
            }
        }
        return attackingValue * ATTACKER_WEIGHTS[Math.min(attackingPieces.size() - 1, 6)] / 100;
    }

    private static Set<Position> getKingZone(Piece king) {
        Set<Position> kingZone = new HashSet<>();
        int[] forward = king.getColour() == Colour.WHITE ? new int[]{-1, 2} : new int[]{-2, 1};

        // King zone consists of the king's position, king's attacking positions, and another row forward
        for (int i = -1; i <= 1; i++) {
            for (int j = forward[0]; j <= forward[1]; j++) {
                Position square = king.getPosition().offset(i, j);
                if (square != null) {
                    kingZone.add(square);
                }
            }
        }
        return kingZone;
    }

    private static int pieceMobility(Colour colour, Board board) {
        int mobility = 0;
        for (Piece piece : board.getChessBoard().values()) {
            if (piece.getColour() == colour) {
                int movesCount = MOVE_VALIDATOR.getValidMoves(piece, board).size();
                switch (piece.getType()) {
                    case KNIGHT -> mobility += movesCount * 4;
                    case BISHOP -> mobility += movesCount * 3;
                    case ROOK -> mobility += movesCount * 2;
                    case QUEEN -> mobility += movesCount;
                }
            }
        }
        double phase = (double) computePhase(board) / PHASE_TOTAL;
        return (int) phase * mobility;
    }
}
