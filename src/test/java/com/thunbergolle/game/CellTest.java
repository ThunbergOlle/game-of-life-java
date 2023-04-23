package com.thunbergolle.game;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thunbergolle.errors.NegativePositionException;

public class CellTest {
    @Test
    public void testCell() {
        Cell cell = new Cell(4, 1, State.ALIVE);
        assertEquals(cell.getPosX(), Integer.valueOf(4));
        assertEquals(cell.getPosY(), Integer.valueOf(1));
        assertEquals(true, cell.isAlive());
    }

    @Test
    public void testCellNegativePosition() {

        try {
            new Cell(-1, 1, State.ALIVE);
            assertEquals(true, false);
        } catch (NegativePositionException e) {
            assertEquals("Invalid position", e.getMessage());
        }
        try {
            new Cell(0, -1, State.ALIVE);
            assertEquals(true, false);
        } catch (NegativePositionException e) {
            assertEquals("Invalid position", e.getMessage());
        }
        try {
            new Cell(null, 1, State.ALIVE);
            assertEquals(true, false);
        } catch (NegativePositionException e) {
            assertEquals("Invalid position", e.getMessage());
        }
        try {
            new Cell(0, null, State.ALIVE);
            assertEquals(true, false);
        } catch (NegativePositionException e) {
            assertEquals("Invalid position", e.getMessage());
        }
    }

    @Test
    public void testCalculateNextState() {
        Cell cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(0);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(1);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(2);
        cell.updateState();
        assertEquals(true, cell.isAlive());

        cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(3);
        cell.updateState();
        assertEquals(true, cell.isAlive());

        cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(4);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.DEAD);
        cell.calculateNextState(3);
        cell.updateState();
        assertEquals(true, cell.isAlive());

        cell = new Cell(0, 0, State.DEAD);
        cell.calculateNextState(2);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.DEAD);
        cell.calculateNextState(-5);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.DEAD);
        cell.calculateNextState(null);
        cell.updateState();
        assertEquals(false, cell.isAlive());
    }

    @Test
    public void testUpdateState() {
        Cell cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(0);
        cell.updateState();
        assertEquals(false, cell.isAlive());

        cell = new Cell(0, 0, State.ALIVE);
        cell.calculateNextState(8);
        assertEquals(true, cell.isAlive());
    }

    @Test
    public void testGetAliveNeighbours() {

        List<Cell> cells = new ArrayList<Cell>() {
            {
                add(new Cell(0, 0, State.ALIVE));
                add(new Cell(0, 1, State.ALIVE));
                add(new Cell(0, 2, State.ALIVE));
                add(new Cell(1, 0, State.ALIVE));
                add(new Cell(1, 1, State.ALIVE));
                add(new Cell(1, 2, State.ALIVE));
                add(new Cell(2, 0, State.ALIVE));
                add(new Cell(2, 1, State.ALIVE));
                add(new Cell(2, 2, State.ALIVE));
            }
        };

        Cell cell = new Cell(1, 1, State.ALIVE);
        Integer aliveNeighbours = cell.getAliveNeighbours(cells);
        assertEquals(Integer.valueOf(8), aliveNeighbours);

        // if one cell is dead, it should not be counted
        Cell deadCell = new Cell(0, 0, State.DEAD);
        cells.set(0, deadCell);
        aliveNeighbours = cell.getAliveNeighbours(cells);
        assertEquals(Integer.valueOf(7), aliveNeighbours);

    }
}
