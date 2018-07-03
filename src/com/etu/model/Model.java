package com.etu.model;


import java.awt.*;
import java.util.Scanner;

public class Model {

    private Field field;
    private Point start;
    private Point finish;
    private double[][] heuristic;
    private double[][] function_g;
    private double[][] function_f;


    private Model(Field field, Point start, Point finish, double[][] heuristic, double[][] function_g, double[][] function_f) {
        this.field = field;
        this.start = start;
        this.finish = finish;
        this.heuristic = heuristic;
        this.function_g = function_g;
        this.function_f = function_f;
    }


    public static double countHeuristic(int curX, int curY, int finX, int finY){
        return Math.sqrt(Math.pow((finY - curY), 2) + Math.pow((finX - curX), 2));
    }

    public boolean isActiveFull() {
        boolean flag = false;
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {
                if(field.getSector(i, j) == Field.Sector.ACTIVE)
                    flag = true;
            }

        }
        return flag;
    }

    public static Model load(Scanner scanner)
    {
        Field field = Field.load(scanner);
        Point start = new Point(scanner.nextInt(), scanner.nextInt());
        Point finish = new Point(scanner.nextInt(), scanner.nextInt());
        double[][] heuristic = new double[field.getNumRows()][field.getNumColumns()];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {

                heuristic[i][j] = countHeuristic(i, j, finish.x, finish.y);
            }

        }
        double[][] function_g = new double[field.getNumRows()][field.getNumColumns()];
        double[][] function_f = new double[field.getNumRows()][field.getNumColumns()];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {

                function_g[i][j] = Double.MAX_VALUE;
                function_f[i][j] = Double.MAX_VALUE;
            }

        }

        return new Model(field, start, finish, heuristic, function_g, function_f);


    }





    public Point min_f()
    {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {
                Point temp = new Point(i, j);
                if(field.isActive(temp) && getFunction_f(temp) < min)
                    min = getFunction_f(temp);
            }

        }
        Point ret = new Point();
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {
                Point temp = new Point(i,j);
                if(field.isActive(temp) && getFunction_f(temp) == min)
                    ret.setLocation(i, j);
            }

        }
        return ret;
    }

    public double getFunction_g(Point current) {
        return function_g[current.x][current.y];
    }

    public void setFunction_g(Point current, double setter) {
        function_g[current.x][current.y] = setter;
    }

    public double getFunction_f(Point current) {
        return function_f[current.x][current.y];
    }

    public void setFunction_f(Point current, double setter) {
        function_f[current.x][current.y] = setter;
    }

    public Field getField() {
        return field;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getFinish() {
        return finish;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public double[][] getHeuristic() {
        return heuristic;
    }


}
