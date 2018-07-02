package com.etu;


import java.awt.*;
import java.util.Scanner;

public class Model {

    private Field field;
    private Point start;
    private Point finish;
    private double[][] heuristic;


    private Model(Field field, Point start, Point finish, double[][] heuristic) {
        this.field = field;
        this.start = start;
        this.finish = finish;
        this.heuristic = heuristic;
    }


    private double countHeuristic(int x, int y){
        return Math.sqrt(Math.pow((finish.y - y), 2) + Math.pow((finish.x - x), 2));
    }

    public Model load(Scanner scanner)
    {
        Field field = Field.load(scanner);
        Point start = new Point(scanner.nextInt(), scanner.nextInt());
        Point finish = new Point(scanner.nextInt(), scanner.nextInt());
        double[][] heuristic = new double[field.getNumRows()][field.getNumColumns()];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {
                heuristic[i][j] = countHeuristic(i, j);
            }

        }
        return new Model(field, start, finish, heuristic);


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
