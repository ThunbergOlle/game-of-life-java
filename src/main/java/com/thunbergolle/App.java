package com.thunbergolle;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.thunbergolle.game.Cell;
import com.thunbergolle.game.GameManager;
import com.thunbergolle.ui.UIManager;

public class App {

    public static void main(String[] args) {

        UIManager uiManager = UIManager.getInstance();
        GameManager gameManager = GameManager.getInstance();

        /* Initialize UI and Game */
        try {
            uiManager.createWindow(Settings.TITLE, Settings.GRID_SIZE, Settings.CELL_RENDER_SIZE);
            gameManager.startGame(Settings.GRID_SIZE);
        } catch (Exception e) {
            System.err.println("Failed to start game");
            e.printStackTrace();
            System.exit(1);
        }

        /*
         * This is the main loop of the game. It will run every 100ms and update the UI
         */
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                UIManager uiManager = UIManager.getInstance();
                GameManager gameManager = GameManager.getInstance();

                /*
                 * NOTE: I took the "React" approach where we re-render completely when state
                 * updates
                 * This is to reduce complexity and avoid having to keep track of what has
                 * changed. But it is not the most efficient way to do it. If performance is
                 * needed, we should only update the squares that has changed.
                 */
                uiManager.clearSquares(); // <--- This is the "re-render" part
                gameManager.update(); // update game state

                List<Cell> cells = gameManager.getCells(); // get the updated cells

                /* Render alive cells to the UI */
                for (Cell cell : cells) {
                    if (cell.isAlive()) {
                        uiManager.addSquare(cell.getPosX(), cell.getPosY(), Settings.CELL_RENDER_SIZE);
                    }
                }
            }
        }, 0, 100);

    }

}
