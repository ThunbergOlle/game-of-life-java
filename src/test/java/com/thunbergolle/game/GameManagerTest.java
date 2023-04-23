package com.thunbergolle.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.thunbergolle.errors.InvalidGridSizeException;

public class GameManagerTest {

    @Test
    public void testStartGame() {
        GameManager gm = GameManager.getInstance();
        gm.startGame(10);
        assertEquals(100, gm.getCells().size());
        for (Cell cell : gm.getCells()) {
            assertEquals(true, cell.getPosX() < 10);
            assertEquals(true, cell.getPosY() < 10);
        }
        
        try {
            gm.startGame(-1);
            assertEquals(true, false);
        } catch (InvalidGridSizeException e) {
            assertEquals("Invalid grid size. The size must be greater than 0. Got: -1", e.getMessage());
        }
    }

    @Test
    public void testUpdate() {
        GameManager gm = GameManager.getInstance();

        gm.startGame(10);
        Integer aliveCells = 0;
        for (Cell cell : gm.getCells()) {
            if (cell.isAlive()) {
                aliveCells++;
            }
        }
        gm.update();
        Integer aliveCellsAfterUpdate = 0;
        for (Cell cell : gm.getCells()) {
            if (cell.isAlive()) {
                aliveCells++;
            }
        }

        System.out.println("Alive cells: " + aliveCells);
        System.out.println("Alive cells after update: " + aliveCellsAfterUpdate);

        assertNotEquals(aliveCells, aliveCellsAfterUpdate);

    }
}
