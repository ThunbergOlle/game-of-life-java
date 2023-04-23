package com.thunbergolle.game;

import java.util.ArrayList;
import java.util.List;

import com.thunbergolle.Settings;
import com.thunbergolle.errors.InvalidGridSizeException;
import com.thunbergolle.errors.NegativePositionException;

public class GameManager {

    private List<Cell> cells = new ArrayList<Cell>();

    private static GameManager instance = null;

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(Integer gridSize) throws NegativePositionException, InvalidGridSizeException {
        try {
            if (gridSize == null || gridSize < 0) {
                throw new InvalidGridSizeException(gridSize);
            }
            Integer amountOfCells = gridSize * gridSize; // Limits to square grid
            for (int i = 0; i < amountOfCells; i++) {
                Integer posX = i % gridSize;
                Integer posY = i / gridSize;
                
                State intitialState = Math.random() < 0.5 ? State.DEAD : State.ALIVE; // select random state initial state
                Cell cell = new Cell(posX, posY, intitialState);
                this.cells.add(cell);
            }
        } catch (NegativePositionException e) {
            System.err.println("Invalid was position was given to cell when starting game");
            throw e;
        } catch (InvalidGridSizeException e) {
            System.err.println(
                    "Invalid grid size was given when starting game. The size must be greater than 0 and can be set inside "
                            + Settings.class.getName() + " class");
            throw e;
        }

    }

    /* Runs "game-loop" for all the cells. */
    public void update() {
        for (Cell cell : this.cells) {
            Integer aliveNeighbours = cell.getAliveNeighbours(this.cells);
            cell.calculateNextState(aliveNeighbours);
        }
        for (Cell cell : this.cells) {
            cell.updateState();
        }
    }

    public List<Cell> getCells() {
        return this.cells;
    }

}
