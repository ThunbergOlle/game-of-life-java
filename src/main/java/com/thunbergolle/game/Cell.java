package com.thunbergolle.game;

import java.util.List;

import com.thunbergolle.errors.NegativePositionException;

public class Cell {
    private Integer posX;
    private Integer posY;
    private State state;
    private State nextState = State.DEAD;

    public Cell(Integer initialPosX, Integer initialPosY, State initialState) {
        this.state = initialState;
        if (initialPosX == null || initialPosY == null || initialPosX < 0 || initialPosY < 0) {
            throw new NegativePositionException();
        }
        this.posX = initialPosX;
        this.posY = initialPosY;
    }

    /* Protect position from being updated from outside class. */
    public Integer getPosX() {
        return this.posX;
    }

    public Integer getPosY() {
        return this.posY;
    }

    public Boolean isAlive() {
        return this.state == State.ALIVE;
    }

    /*
     * Returns the amount of alive neighbours. Does not modify any state.
     */
    public Integer getAliveNeighbours(List<Cell> cells) {
        Integer aliveNeighbours = 0;
        for (Cell cell : cells) {
            if (cell.isAlive()) {
                /*
                 * Checks:
                 * 000
                 * 0X0
                 * 100
                 */
                if (cell.getPosX() == posX - 1 && cell.getPosY() == posY - 1) {
                    aliveNeighbours++;
                }
                /*
                 * 000
                 * 0X0
                 * 010
                 */
                if (cell.getPosX() == posX && cell.getPosY() == posY - 1) {
                    aliveNeighbours++;
                }
                /*
                 * 000
                 * 0X0
                 * 001
                 */
                if (cell.getPosX() == posX + 1 && cell.getPosY() == posY - 1) {
                    aliveNeighbours++;
                }
                /*
                 * 000
                 * 1X0
                 * 000
                 */
                if (cell.getPosX() == posX - 1 && cell.getPosY() == posY) {
                    aliveNeighbours++;
                }
                /*
                 * 000
                 * 0X1
                 * 000
                 */
                if (cell.getPosX() == posX + 1 && cell.getPosY() == posY) {
                    aliveNeighbours++;
                }
                /*
                 * 100
                 * 0X0
                 * 000
                 */
                if (cell.getPosX() == posX - 1 && cell.getPosY() == posY + 1) {
                    aliveNeighbours++;
                }
                /*
                 * 010
                 * 0X0
                 * 000
                 */
                if (cell.getPosX() == posX && cell.getPosY() == posY + 1) {
                    aliveNeighbours++;
                }
                /*
                 * 001
                 * 0X0
                 * 000
                 */
                if (cell.getPosX() == posX + 1 && cell.getPosY() == posY + 1) {
                    aliveNeighbours++;
                }
            }
        }
        return aliveNeighbours;
    }

    /*
     * Calculates the next state of the cell based on the amount of alive
     * neighbours.
     * Updates the next state of the cell.
     */
    public void calculateNextState(Integer aliveNeighbours) {
        if (aliveNeighbours == null) {
            aliveNeighbours = 0; // Don't allow null values.
        }
        if (this.isAlive()) {
            if (aliveNeighbours < 2) {
                this.nextState = State.DEAD;
            } else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
                this.nextState = State.ALIVE;
            } else if (aliveNeighbours > 3) {
                this.nextState = State.DEAD;
            }
        } else {
            if (aliveNeighbours == 3) {
                this.nextState = State.ALIVE;
            }
        }
    }

    /*
     * Updates the state of the cell to the next state.
     */
    public void updateState() {
        this.state = this.nextState;
    }
}
