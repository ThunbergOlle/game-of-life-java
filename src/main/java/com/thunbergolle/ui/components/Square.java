package com.thunbergolle.ui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

public class Square extends JComponent {

    Integer renderPosX, renderPosY;
    Integer squareSize;
    Graphics2D g2d;

    public Square(Integer posX, Integer posY, Integer squareSize) {
        /* The render position differs from the "grid" position. */
        this.renderPosX = posX * squareSize;
        this.renderPosY = posY * squareSize;
        this.squareSize = squareSize;
        this.setSize(squareSize, squareSize);
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics.create();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(this.renderPosX, this.renderPosY, squareSize, squareSize);
        g2d.dispose();
    }

}
