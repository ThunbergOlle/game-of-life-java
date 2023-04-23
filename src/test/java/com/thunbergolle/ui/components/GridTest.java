package com.thunbergolle.ui.components;

import static org.junit.Assert.assertEquals;

import java.awt.Dimension;

import org.junit.Test;

public class GridTest {
    @Test
    public void testGrid() {
        Grid grid = new Grid(40, 15);
        Dimension size = grid.getPreferredSize();
        
        assertEquals(600, size.width);
        Grid gridSmall = new Grid(1, 1);
        Dimension sizeSmall = gridSmall.getPreferredSize();
        assertEquals(1, sizeSmall.width);

    }
}
