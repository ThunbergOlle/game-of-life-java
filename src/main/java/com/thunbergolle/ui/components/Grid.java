package com.thunbergolle.ui.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Grid extends JPanel {


    private Integer gridRenderSize;
    private Integer cellRenderSize;
    private Integer gridSize;

    public Grid(Integer gridSize, Integer cellRenderSize) {
        this.cellRenderSize = cellRenderSize;
        this.gridSize = gridSize;
        this.gridRenderSize = gridSize * cellRenderSize;
        this.setPreferredSize(new Dimension(this.gridRenderSize, this.gridRenderSize));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        /* Draw the grid */
        for (int i = 0; i < gridSize; i++) {
            g2d.drawLine(i * cellRenderSize, 0, i * cellRenderSize, this.gridRenderSize);
            g2d.drawLine(0, i * cellRenderSize, this.gridRenderSize, i * cellRenderSize);
        }
        g2d.dispose();
    }

}