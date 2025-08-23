package com.friendprojects.chessapp.util;

import com.friendprojects.chessapp.enums.PieceType;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Move;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.rules.OutcomeDetector;
import com.friendprojects.chessapp.rules.RulesEngine;

import java.util.List;

public class SANUtil {

    public static String toAlgebraic(Move move, Board board, RulesEngine rulesEngine, OutcomeDetector outcomeDetector) {
        StringBuilder sb = new StringBuilder();
        Piece piece = move.getPiece();

        // Castling
        if (move.isCastling()) {
            boolean isQueenSideCastle = move.getTarget().getCol() - move.getCastlingRookMove().getTarget().getCol() == -1;
            sb.append(isQueenSideCastle ? "O-O-O" : "O-O");
            return sb.toString();
        }

        // Piece Type
        if (piece.getType() == PieceType.PAWN) {
            if (move.isCapturing()) {
                sb.append(move.getOrigin().toAlgebraic().charAt(0));
            }
        } else {
            sb.append(piece.getType().getSymbol());
        }

        // Disambiguating
        List<Move> legalMoves = rulesEngine.getLegalMoves(piece.getColour(), board);
        boolean sameColumn = false;
        boolean sameRow = false;
        for (Move legalMove : legalMoves) {
            if (move.equals(legalMove)) continue;
            if (move.getTarget().equals(legalMove.getTarget()) && move.getPiece().getType() == legalMove.getPiece().getType()) {
                if (move.getOrigin().getCol() == legalMove.getOrigin().getCol()) {
                    sameColumn = true;
                }
                if (move.getOrigin().getRow() == legalMove.getOrigin().getRow()) {
                    sameRow = true;
                }
            }
        }
        if (sameColumn) sb.append(move.getOrigin().toAlgebraic().charAt(0));
        if (sameRow) sb.append(move.getOrigin().toAlgebraic().charAt(1));

        // Capturing (Minus Pawn)
        if (move.isCapturing()) {
            sb.append("x");
        }

        // Target Square
        sb.append(move.getTarget().toAlgebraic());

        // Promotion
        if (move.isPawnPromotion()) {
            sb.append(move.getPromotionType().getSymbol());
        }

        // Check and Checkmate
        if (rulesEngine.isKingInCheck(piece.getColour(), board)) {
            sb.append("+");
        } else if (outcomeDetector.isCheckmate(piece.getColour(), board)) {
            sb.append("#");
        }

        return sb.toString();
    }
}
