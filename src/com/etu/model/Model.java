package com.etu.model;


import java.awt.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Model {

    private Field field;
    private Point start;
    private Point finish;
    private double[][] heuristic;
    private double[][] function_g;
    private double[][] function_f;
    private Point[][] from;
    private StringBuilder comments;


    private Model(Field field, Point start, Point finish, double[][] heuristic, double[][] function_g, double[][] function_f, Point[][] from, StringBuilder comments) {
        this.field = field;
        this.start = start;
        this.finish = finish;
        this.heuristic = heuristic;
        this.function_g = function_g;
        this.function_f = function_f;
        this.from = from;
        this.comments = comments;
    }




    public static double countHeuristic(int curX, int curY, int finX, int finY){
        return Math.sqrt(Math.pow((finY - curY), 2) + Math.pow((finX - curX), 2));
//        return Math.abs(finY - curY) + Math.abs(finX - curX);
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

    public Set<Point> getNotUnActiveNeighbours(Point current)
    {
        Set<Point> ret = new HashSet<>();
        if(((current.x+1) >= 0) && ((current.x+1) < field.getNumRows()))
        {
            if ((field.getSector(current.x + 1, current.y) == Field.Sector.ACTIVE)
                    || (field.getSector(current.x + 1, current.y) == Field.Sector.FREE))
                ret.add(new Point(current.x + 1, current.y));
        }
        if(((current.x-1) >= 0) && ((current.x-1) < field.getNumRows()))
        {
            if ((field.getSector(current.x - 1, current.y) == Field.Sector.ACTIVE)
                    || (field.getSector(current.x - 1, current.y) == Field.Sector.FREE))
                ret.add(new Point(current.x - 1, current.y));
        }
        if(((current.y) >= 0) && ((current.y+1) < field.getNumColumns()))
        {
            if ((field.getSector(current.x, current.y + 1) == Field.Sector.ACTIVE)
                    || (field.getSector(current.x, current.y + 1) == Field.Sector.FREE))
                ret.add(new Point(current.x, current.y + 1));
        }
        if(((current.y-1) >= 0) && ((current.y-1) < field.getNumColumns()))
        {
            if ((field.getSector(current.x, current.y - 1) == Field.Sector.ACTIVE)
                    || (field.getSector(current.x, current.y - 1) == Field.Sector.FREE))

                ret.add(new Point(current.x, current.y - 1));
        }
        return ret;
    }

    public static Model load()
    {
        Field field = Field.load();
        Point start = new Point(0,0);
        Point finish = start;
        double[][] heuristic = new double[10][10];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {
                heuristic[i][j] = 0;
            }
        }
        double[][] function_g = new double[10][10];
        double[][] function_f = new double[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                function_g[i][j] = Double.MAX_VALUE;
                function_f[i][j] = Double.MAX_VALUE;
            }

        }
        Point[][] from = new Point[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                from[i][j] = new Point(i, j);
            }

        }
        StringBuilder comments = new StringBuilder();
        return new Model(field, start, finish, heuristic, function_g, function_f, from, comments);
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
                System.out.print((int)heuristic[i][j] + " ");
            }
            System.out.println(" ");

        }
        double[][] function_g = new double[field.getNumRows()][field.getNumColumns()];
        double[][] function_f = new double[field.getNumRows()][field.getNumColumns()];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++) {

                function_g[i][j] = Double.MAX_VALUE;
                function_f[i][j] = Double.MAX_VALUE;
            }

        }
        Point[][] from = new Point[field.getNumRows()][field.getNumColumns()];
        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns() ; j++) {
                from[i][j] = new Point(i, j);
            }

        }
        StringBuilder comments = new StringBuilder();
        return new Model(field, start, finish, heuristic, function_g, function_f, from, comments);


    }

    public StringBuilder getComments()
    {
        return this.comments;
    }

    public void setFrom(Point current, Point setter)
    {
        from[current.x][current.y].move(setter.x, setter.y);

    }

    public Point getFrom(Point current)
    {
        return new Point(current.x, current.y);
    }

    public Point[][] getFrom()
    {
        return this.from;
    }

    public Point min_f()
    {
      //  System.out.println("----------------------------------");
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
                    ret.move(i, j);
            }

        }
      //  System.out.println(getFunction_f(ret) +" "+ ret);
        return ret;
    }

//    public clear

    public double getFunction_g(Point current) {
        return function_g[current.x][current.y];
    }

    public double[][] getFunction_f() { return this.function_f; }

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

    public void setStart(Point start) { this.start = start; }

    public Point getFinish() {
        return finish;
    }

    public void setFinish(Point finish) {
        this.finish = finish;
    }

    public double[][] getHeuristic() {
        return heuristic;
    }

    public void setWall(Point wall){ field.setSectorWall(wall); }

    public void setEmpty(Point cords) { field.setSectorFree(cords);}

    public boolean isWall(Point temp){ return field.getSector(temp.x,temp.y) == Field.Sector.WALL;}



    //todo test this function!!

    public void clear() {

        for (int i = 0; i < field.getNumRows(); i++) {
            for (int j = 0; j < field.getNumColumns(); j++)
                if (field.getSector(i, j) != Field.Sector.WALL) field.setSectorFree(new Point(i, j));
        }


    }
}
