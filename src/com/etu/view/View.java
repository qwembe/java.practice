package com.etu.view;

import com.etu.model.Field;
import com.etu.model.Model;

import java.awt.*;

public class View {


    private final static int HEADER_HEIGHT = 40;

    private final static int CELL_SIZE = 40;
    private final static int BORDER_SIZE = 5;


    private Graphics graphics;

    public void draw(Model model) {
        //drawHeader();
        drawField(model.getField());
        //drawCheckPoints(model.getBonuses());
        //drawFinishPoint(model.getFinish());
        //drawBall(model.getBall());
    }
/*
    @SuppressWarnings("SuspiciousNameCombination")
    private void drawHeader() {
       // int height = HEADER_HEIGHT - 2;
       graphics.drawRect(0, 0, CELL_SIZE * 10, 25, Color.WALL.getRGB());
       graphics.drawRect(0, 0, CELL_SIZE * 10, 2, Color.BALL.getRGB());
    }
*/
    private void drawField(Field field) {
        for (int x = 0; x < field.getNumRows(); x++) {
            for (int y = 0; y < field.getNumColumns(); y++) {
//                Color color = field.getSector(x, y) == Field.Sector.WALL ? Color.WALL : Color.GROUND;
                Color color = Color.GROUND;;
                switch (field.getSector(x, y)){
                    case WALL: color = Color.WALL; break;
                    case FREE: color = Color.GROUND; break;
                    case REALWAY: color = Color.WAY;break;


                }
                graphics.drawRect( x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, color.getRGB());
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
        BONUS_CHECKED(java.awt.Color.GREEN.getRGB()),
        BONUS_UNCHECKED(java.awt.Color.ORANGE.getRGB()),
        WALL(java.awt.Color.BLUE.getRGB()),//new java.awt.Color(162, 129, 39).getRGB()),
        GROUND(java.awt.Color.WHITE.getRGB()),//new java.awt.Color(202, 203, 204).getRGB()),
        WAY(java.awt.Color.GREEN.getRGB()),
        ACTIVE(java.awt.Color.YELLOW.getRGB()),
        HEADER(new java.awt.Color(169, 180, 192).getRGB()),
        BORDER(new java.awt.Color(0, 0, 0).getRGB()),
        BALL(java.awt.Color.BLUE.getRGB()),
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
