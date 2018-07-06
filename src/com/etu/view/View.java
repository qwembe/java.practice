package com.etu.view;

import com.etu.model.Field;
import com.etu.model.Model;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class View {

    private  final static int CANVAS_SIZE = 500;


    private  static int CELL_SIZE_X ;
    private  static int CELL_SIZE_Y ;

    private  static int ADDED_X ;
    private  static int ADDED_Y ;

    private  static int ADDED_HX;

    private JTextArea log;
    private Graphics2D graphics;

    public void setLog(JTextArea log) {
        this.log = log;
    }

    public  JTextArea getLog() {
        return log;
    }

    public void draw(Model model) {
        updateCells(model.getField());
        drawField(model);
    }

    private void updateCells(Field field){
        CELL_SIZE_X = CANVAS_SIZE / field.getNumRows();
        CELL_SIZE_Y = CANVAS_SIZE / field.getNumColumns();
        ADDED_X = CELL_SIZE_X / 2;
        ADDED_Y = CELL_SIZE_Y / 2;
        ADDED_HX = CELL_SIZE_X / 2;


    }


    private void drawField(Model model) {
        Field field = model.getField();
        Point pointFinish = model.getFinish();
        Point pointStart = model.getStart();
        DecimalFormat df = new DecimalFormat("#.##");
        double[][] her = model.getFunction_f();
        double[][] eur = model.getHeuristic();
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
                graphics.drawRect( x * CELL_SIZE_X, y * CELL_SIZE_Y, CELL_SIZE_Y, CELL_SIZE_X, color.getRGB());
                color = Color.INFOCELL_1;
// inverted coords!!!
                if(model.getField().getNumRows() <= 16 || model.getField().getNumColumns() <= 16){
                    graphics.drawText((y)*CELL_SIZE_Y,(x+1)*CELL_SIZE_X,df.format(her[x][y] == Double.MAX_VALUE ? Double.NaN : her[x][y]),color.getRGB());
                    color = Color.INFOCELL_2;
                    graphics.drawText((y)*CELL_SIZE_Y + ADDED_Y,(x+1)*CELL_SIZE_X - ADDED_X - CELL_SIZE_X /4,"(" + x + "," + y + ")",color.getRGB());
                    graphics.drawText((y)*CELL_SIZE_Y + ADDED_Y,(x+1)*CELL_SIZE_X - ADDED_X,df.format(eur[x][y]),color.getRGB());
                }

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
        INFOCELL_1(java.awt.Color.BLACK.getRGB()),
        INFOCELL_2(java.awt.Color.GRAY.getRGB()),
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
        return new Point(mousePoint.y /CELL_SIZE_X,mousePoint.x / CELL_SIZE_Y);
    }

}
