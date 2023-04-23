package com.thunbergolle.ui.components;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SquareTest {
    @Test
    public void testSquare() {
        Square square = new Square(1, 5, 10);

        assertEquals(10, square.getWidth());
        assertEquals(10, square.getHeight());

        assertEquals(Integer.valueOf(10), square.renderPosX);
        assertEquals(Integer.valueOf(50), square.renderPosY);
    }
}
