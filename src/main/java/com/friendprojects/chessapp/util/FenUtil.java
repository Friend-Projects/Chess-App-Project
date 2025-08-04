package com.friendprojects.chessapp.util;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Game;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;

public class FenUtil {

    public static String generateFEN(Game game) {
        StringBuilder sb = new StringBuilder();
        Board board = game.getBoard();

        for (int i = 7; i >= 0; i--) {
            int numEmpty = 0;
            for (int j = 0; j <= 7; j++) {
                Position position = new Position(i, j);
                Piece piece = board.getPieceAt(position);
                if (piece == null) {
                    numEmpty++;
                } else {
                    if (numEmpty > 0) {
                        sb.append(numEmpty);
                        numEmpty = 0;
                    }
                    sb.append(pieceToFENChar(piece));
                }
            }
            if (numEmpty > 0) {
                sb.append(numEmpty);
            }
            sb.append('/');
        }

        sb.append(' ').append(game.getCurrentTurn().getColor() == Colour.WHITE ? 'w' : 'b');
        sb.append(' ').append(getCastlingInfo(board));
        sb.append(' ').append(getEnpassantInfo(board));
        sb.append(' ').append(getHalfMoveInfo(game));
        sb.append(' ').append(getFullMoveInfo(game));
        return sb.toString();
    }

    private static char pieceToFENChar(Piece piece) {
        char fenChar;
        switch (piece.getType()) {
            case PAWN -> fenChar = 'p';
            case KNIGHT -> fenChar = 'n';
            case BISHOP -> fenChar = 'b';
            case ROOK -> fenChar = 'r';
            case QUEEN -> fenChar = 'q';
            case KING -> fenChar = 'k';
            default -> throw new IllegalArgumentException("[Piece type not found] " + piece.getType());
        }
        return piece.getColour() == Colour.WHITE ? Character.toUpperCase(fenChar) : fenChar;
    }

    private static String getCastlingInfo(Board board) {
        return null;
    }

    private static String getEnpassantInfo(Board board) {
        return null;
    }

    private static String getHalfMoveInfo(Game game) {
        return null;
    }

    private static String getFullMoveInfo(Game game) {
        return null;
    }
}
