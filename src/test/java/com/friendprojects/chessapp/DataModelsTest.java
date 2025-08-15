package com.friendprojects.chessapp;

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
        public void testEqualityComparison() {
            Position one = new Position(2, 3);
            Position two = new Position(2, 3);
            assertTrue(one.equals(two));
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
            assertEquals(null, one.offset(4, 4));
        }
    }

    @Nested
    class PieceTest {

    }

    @Nested
    class MoveTest {

    }

    @Nested
    class PlayerTest {

    }

    @Nested
    class TimeControlTest {

    }

    @Nested
    class BoardTest {

    }

    @Nested
    class GameTest {

    }
}
