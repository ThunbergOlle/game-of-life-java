package com.thunbergolle.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.thunbergolle.errors.InvalidGridSizeException;
import com.thunbergolle.errors.InvalidSquareSizeException;
import com.thunbergolle.ui.components.Grid;
import com.thunbergolle.ui.components.Square;

public class UIManager {

    Grid grid;

    /*
     * Note that UI should not need to know what a "CELL" is.
     * The UI should only know about squares.
     */
    List<Square> squares = new ArrayList<>();

    /* Using singelton pattern for UIManager */
    private static UIManager instance = null;

    private UIManager() {
    }

    public static UIManager getInstance() {
        if (instance == null) {
            instance = new UIManager();
        }
        return instance;
    }

    public void createWindow(String title, Integer gridSize, Integer squareSize) {
        if (gridSize == null || gridSize < 0) {
            throw new InvalidGridSizeException(gridSize);
        }
        if (squareSize == null || squareSize < 0) {
            throw new InvalidSquareSizeException(squareSize);
        }
        JFrame frame = new JFrame(title);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Create grid */
        this.grid = new Grid(gridSize, squareSize);
        frame.add(this.grid);

        /* Resize the window accordingly */
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /*
     * Adds a square to the grid
     */
    public void addSquare(Integer posX, Integer posY, Integer squareSize) {
        Square s = new Square(posX, posY, squareSize);
        this.grid.add(s);
        s.setVisible(true);
        s.setSize(grid.getSize());
        this.squares.add(s);
    }

    public void clearSquares() {
        for (Square s : this.squares) {
            this.grid.remove(s);
        }
        this.squares.clear();
    }

}
