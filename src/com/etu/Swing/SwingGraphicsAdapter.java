package com.etu.Swing;

import com.etu.view.Graphics;

import javax.swing.*;
import java.awt.*;

public class SwingGraphicsAdapter implements Graphics {

    private final java.awt.Graphics graphics;

    public SwingGraphicsAdapter(java.awt.Graphics graphics) {
        this.graphics = graphics;
    }

    @Override
    @SuppressWarnings("SuspiciousNameCombination")
    public void drawRect(int x, int y, int width, int height, int rgb) {

        graphics.setColor(new Color(rgb));
        graphics.fillRect(y, x, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(y,x,width,height);
    }


    @Override
    public void drawText(int x, int y, String text, int rgb) {
        char[] symbols = text.toCharArray();
        graphics.setColor(new Color(rgb));
        graphics.drawChars(symbols, 0, symbols.length, x, y);
    }

}
