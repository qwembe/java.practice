package com.etu.view;

import com.etu.model.Field;
import com.etu.model.Model;

import java.awt.*;
import java.text.DecimalFormat;

public class View {


    private final static int HEADER_HEIGHT = 40;

    private final static int CELL_SIZE = 50;
    private final static int BORDER_SIZE = 5;


    private Graphics graphics;

    public void draw(Model model) {
        //drawHeader();
        drawField(model);
//        drawStart(model);
//        drawEnd(model);
        drawHeuristic(model);
//        drawCheckPoints(model.getBonuses());
//        drawFinishPoint(model.getFinish());
//        drawBall(model.getBall());
    }

    private void drawHeuristic(Model model) {
        Field field = model.getField();
        DecimalFormat df = new DecimalFormat("#.##");
        double[][] her = model.getHeuristic();
        Color color = Color.INFOCELL;
        for (int x = 0; x < field.getNumRows(); x++) {
            for (int y = 0; y < field.getNumColumns(); y++) {
                graphics.drawText((x)*CELL_SIZE,(y+1)*CELL_SIZE,df.format(her[y][x]),color.getRGB());
            }
        }
    }

    /*
        private void drawHeuristic(Model model,int x,int y) {
            DecimalFormat df = new DecimalFormat("#.##");
            Color color = Color.INFOCELL;
            graphics.drawText( (x) * CELL_SIZE , (y+1) * CELL_SIZE , df.format(model.getHeuristic(x, y)), color.getRGB());
        }*/
/*
    private void drawEnd(Model model) {
        Point p = model.getFinish();
        Color col = Color.FINISH;
        graphics.drawRect(p.x * CELL_SIZE,p.y * CELL_SIZE , CELL_SIZE , CELL_SIZE, col.getRGB());
    }

    private void drawStart(Model model) {
        Point p = model.getStart();
        Color col = Color.START;
        graphics.drawRect(p.x * CELL_SIZE,p.y * CELL_SIZE , CELL_SIZE , CELL_SIZE, col.getRGB());
    }
*/
    /*
        @SuppressWarnings("SuspiciousNameCombination")
        private void drawHeader() {
           // int height = HEADER_HEIGHT - 2;
           graphics.drawRect(0, 0, CELL_SIZE * 10, 25, Color.WALL.getRGB());
           graphics.drawRect(0, 0, CELL_SIZE * 10, 2, Color.BALL.getRGB());
        }
    */
    private void drawField(Model model) {
        Field field = model.getField();
//        drawStart(model);
//        drawEnd(model);
        Point pointFinish = model.getFinish();
        Point pointStart = model.getStart();
        for (int x = 0; x < field.getNumRows(); x++) {
            for (int y = 0; y < field.getNumColumns(); y++) {
                Color color = Color.GROUND;
                if (pointStart.x == x && pointStart.y == y) color = Color.START;
                else {
                    if (pointFinish.x == x && pointFinish.y == y) color = Color.FINISH;
                    else {
                        switch (field.getSector(x, y)){
                            case WALL: color = Color.WALL; break;
                            case FREE: color = Color.GROUND; break;
                            case REALWAY: color = Color.WAY;break;
                        }
                    }
                }
                graphics.drawRect( x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, color.getRGB());
                //drawHeuristic(model,y,x);
            }
        }
    }
/*
    private void drawCheckPoints(Set<CheckPoint> bonuses) {
        int bonusesTaken = 0;
        for (CheckPoint bonus : bonuses) {
            Point position = bonus.getPosition();
            int x = position.getX() * CELL_SIZE + CELL_SIZE / 3;
            int y = position.getY() * CELL_SIZE + CELL_SIZE / 3;
            Color color;
            if (bonus.isChecked()) {
                color = Color.BONUS_CHECKED;
                ++bonusesTaken;
            } else {
                color = Color.BONUS_UNCHECKED;
            }
            graphics.drawOval(HEADER_HEIGHT +  x, y, CELL_SIZE / 4, CELL_SIZE / 4, color.getRGB());
        }
        Color textColor = bonusesTaken == bonuses.size() ? Color.BONUS_CHECKED : Color.BONUS_UNCHECKED;
        graphics.drawText(20, 25, "Bonuses taken: " + bonusesTaken + " / " + bonuses.size(), textColor.getRGB());
    }

    private void drawFinishPoint(CheckPoint finish) {
        Point position = finish.getPosition();
        int x = position.getX() * CELL_SIZE + CELL_SIZE / 4;
        int y = position.getY() * CELL_SIZE + CELL_SIZE / 4;
        graphics.drawOval(HEADER_HEIGHT + x, y, CELL_SIZE / 2, CELL_SIZE / 2, Color.FINISH.getRGB());
    }

    private void drawBall(Point ball) {
        int x = ball.getX() * CELL_SIZE + CELL_SIZE / 4;
        int y = ball.getY() * CELL_SIZE + CELL_SIZE / 4;
        graphics.drawOval(HEADER_HEIGHT + x, y, CELL_SIZE / 2, CELL_SIZE / 2, Color.BALL.getRGB());
    }
*/


/*
    public void showCongratsDialog() {
        graphics.showCongratsDialog();
    }

    public void showNeedMoreBonusesDialog() {
        graphics.showNeedMoreBonusesDialog();
    }
*/

    public void setGraphics(Graphics graphics) {
        this.graphics = graphics;
    }


    private enum Color {
//        BONUS_CHECKED(java.awt.Color.GREEN.getRGB()),
//        BONUS_UNCHECKED(java.awt.Color.ORANGE.getRGB()),
        WALL(java.awt.Color.BLUE.getRGB()),//new java.awt.Color(162, 129, 39).getRGB()),
        GROUND(java.awt.Color.WHITE.getRGB()),//new java.awt.Color(202, 203, 204).getRGB()),
        WAY(java.awt.Color.GREEN.getRGB()),
//        ACTIVE(java.awt.Color.YELLOW.getRGB()),
//        HEADER(new java.awt.Color(169, 180, 192).getRGB()),
//        BORDER(new java.awt.Color(0, 0, 0).getRGB()),
        START(java.awt.Color.BLACK.getRGB()),
        INFOCELL(java.awt.Color.BLACK.getRGB()),
        FINISH(java.awt.Color.RED.getRGB());

        private final int rgb;

        Color(int rgb) {
            this.rgb = rgb;
        }

        public int getRGB() {
            return rgb;
        }
    }

}
