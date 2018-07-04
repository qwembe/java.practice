package com.etu.view;

import com.etu.model.Field;
import com.etu.model.Model;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class View {


    private final static int HEADER_HEIGHT = 40;

    private  static int CELL_SIZE = 50;
    private  static int BORDER_SIZE = 5;

    private JTextArea log;
    private Graphics2D graphics;

    public void setLog(JTextArea log) {
        this.log = log;
    }

    public  JTextArea getLog() {
        return log;
    }

    public void draw(Model model) {
        drawField(model);
    }

    private void drawField(Model model) {
        Field field = model.getField();
        Point pointFinish = model.getFinish();
        Point pointStart = model.getStart();
        DecimalFormat df = new DecimalFormat("#.##");
        double[][] her = model.getFunction_f();
        for (int x = 0; x < field.getNumRows(); x++) {
            for (int y = 0; y < field.getNumColumns(); y++) {
                Color color = Color.GROUND;
                if (pointStart.x == x && pointStart.y == y) color = Color.START;
                else {
                    if (pointFinish.x == x && pointFinish.y == y) color = Color.FINISH;
                    else {
                        switch (field.getSector(x, y)){
                            case ACTIVE: color = Color.ACTIVE; break;
                            case CURRENT: color = Color.CURRENT; break;
                            case WALL: color = Color.WALL; break;
                            case FREE: color = Color.GROUND; break;
                            case REALWAY: color = Color.WAY;break;
                            case UNACTIVE: color = Color.UNACTIVE;break;
                        }
                    }
                }
                graphics.drawRect( x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, color.getRGB());
                color = Color.INFOCELL;
// inverted coords!!!
                graphics.drawText((y)*CELL_SIZE,(x+1)*CELL_SIZE,df.format(her[x][y] == Double.MAX_VALUE ? Double.NaN : her[x][y]),color.getRGB());

            }
        }
    }


    public void setGraphics(Graphics2D graphics) {
        this.graphics =  graphics;
    }


    private enum Color {
//        BONUS_CHECKED(java.awt.Color.GREEN.getRGB()),
//        BONUS_UNCHECKED(java.awt.Color.ORANGE.getRGB()),
        WALL(java.awt.Color.BLUE.getRGB()),//new java.awt.Color(162, 129, 39).getRGB()),
        GROUND(java.awt.Color.WHITE.getRGB()),//new java.awt.Color(202, 203, 204).getRGB()),
        WAY(java.awt.Color.RED.getRGB()),
        ACTIVE(java.awt.Color.YELLOW.getRGB()),
//        HEADER(new java.awt.Color(169, 180, 192).getRGB()),
//        BORDER(new java.awt.Color(0, 0, 0).getRGB()),
        START(java.awt.Color.BLACK.getRGB()),
        INFOCELL(java.awt.Color.BLACK.getRGB()),
        FINISH(java.awt.Color.ORANGE.getRGB()),
        CURRENT(java.awt.Color.GREEN.getRGB()),
        UNACTIVE(java.awt.Color.GRAY.getRGB());

        private final int rgb;

        Color(int rgb) {
            this.rgb = rgb;
        }

        public int getRGB() {
            return rgb;
        }
    }

    //case of reverted field i had to revert my coords!!
    public Point getCoords(Point mousePoint){
        return new Point(mousePoint.y / CELL_SIZE,mousePoint.x /CELL_SIZE);
    }

}
