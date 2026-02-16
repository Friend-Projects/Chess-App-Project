package com.friendprojects.chessapp.util;

import com.friendprojects.chessapp.enums.Colour;
import com.friendprojects.chessapp.model.Board;
import com.friendprojects.chessapp.model.Game;
import com.friendprojects.chessapp.model.Piece;
import com.friendprojects.chessapp.model.Position;

import java.util.Arrays;
import java.util.List;

public class FENUtil {

    /**
     * Generate the Forsyth-Edwards Notation of the board's current state in the game.
     *
     * @param game the game of the board to be converted
     * @return the FEN {@code String} generated from the board's positions
     */
    public static String generateFEN(Game game) {
        StringBuilder sb = new StringBuilder(generatePartialFEN(game));
        sb.append(' ').append(getHalfMoveInfo(game));
        sb.append(' ').append(getFullMoveInfo(game));
        return sb.toString();
    }

    public static String generatePartialFEN(Game game) {
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
                    sb.append(piece.toFENChar());
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
        return sb.toString();
    }

    /**
     * Get information on the ability of both players to castle king and queen side and displayed in FEN
     * (FEN records the ability of each player to castle, not if castling is currently a legal move).
     *
     * @param board the board state being converted
     * @return the FEN {@code String} of all castling rights
     */
    private static String getCastlingInfo(Board board) {
        StringBuilder sb = new StringBuilder();
        List<Piece> kings = Arrays.asList(board.getKing(Colour.WHITE), board.getKing(Colour.BLACK));
        int[] rooksOffset = new int[]{3, -4};

        for (Piece king : kings) {
            if (king != null && king.isUnmoved()) {
                Piece kingSideRook = board.getPieceAt(king.getPosition().offset(rooksOffset[0], 0));
                if (kingSideRook != null && kingSideRook.isUnmoved()) {
                    sb.append(king.getColour() == Colour.WHITE ? 'K' : 'k');
                }
                Piece queenSideRook = board.getPieceAt(king.getPosition().offset(rooksOffset[1], 0));
                if (queenSideRook != null && queenSideRook.isUnmoved()) {
                    sb.append(king.getColour() == Colour.WHITE ? 'Q' : 'q');
                }
            }
        }
        if (sb.isEmpty()) {
            return "-";
        } else {
            return sb.toString();
        }
    }

    /**
     * Get the information on whether En Passant is possible for any player on the board
     * (FEN records the square behind the pawn in algebraic notation which performed a double forward last).
     *
     * @param board the board state being converted
     * @return the FEN {@code String} of the en passant capture square
     */
    private static String getEnpassantInfo(Board board) {
        Piece enPassantCapture = board.getEnPassantCapture();
        if (enPassantCapture == null) {
            return "-";
        } else {
            return enPassantCapture.getPosition().offset(enPassantCapture.getColour() == Colour.WHITE ? -1 : 1, 0).toAlgebraic();
        }
    }

    /**
     * Get the number of half moves (single player moved) since the last pawn move or capture.
     *
     * @param game the game containing the board state being converted
     * @return the FEN {@code String} of the number of half moves
     */
    private static String getHalfMoveInfo(Game game) {
        return Integer.toString(game.getHalfMoveClock());
    }

    /**
     * Get the number of full moves (both players moved) the current game has experienced.
     *
     * @param game the game containing the board state being converted
     * @return the FEN {@code String} of the number of full moves
     */
    private static String getFullMoveInfo(Game game) {
        return Integer.toString(game.getFullMoveClock());
    }
}
