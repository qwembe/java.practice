package com.etu.Swing;

import com.etu.view.Graphics2D;

import javax.swing.*;
import java.awt.*;

public class SwingGraphicsAdapter implements Graphics2D {

    private final java.awt.Graphics2D graphics;

    public SwingGraphicsAdapter(java.awt.Graphics2D graphics) {
        this.graphics = graphics;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
