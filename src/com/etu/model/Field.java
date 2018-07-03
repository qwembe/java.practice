
package com.etu.model;
import java.awt.*;
import java.util.HashSet;



import java.util.Scanner;
import java.util.Set;

public class Field {
    private final Sector[][] field;

    private Field(Sector[][] field) {

        this.field = field;
    }

    public int getNumRows() {

        return field.length;
    }

    public int getNumColumns() {
        return field[0].length;
    }

    public boolean isActive(Point current)
    {
        if(field[current.x][current.y] == Sector.ACTIVE)
            return true;
        else return false;
    }



    public Sector getSector(int x, int y) {

        return field[x][y];
    }

    public void setSectorActive(Point current)
    {
        field[current.x][current.y] = Sector.ACTIVE;
    }

    public void setSectorUnActive(Point current)
    {
        field[current.x][current.y] = Sector.UNACTIVE;
    }

    public void setSectorRealWay (Point current) { field[current.x][current.y] = Sector.REALWAY; }

    public void printWay() {
        
    }

    public Set<Point> NotUnActiveNeighbours(Point current)
    {
        Set<Point> ret = new HashSet<>();
        if(field[current.x+1][current.y] != Sector.UNACTIVE) ret.add(new Point(current.x+1, current.y));
        if(field[current.x-1][current.y] != Sector.UNACTIVE) ret.add(new Point(current.x-1, current.y));
        if(field[current.x][current.y+1] != Sector.UNACTIVE) ret.add(new Point(current.x, current.y+1));
        if (field[current.x][current.y-1] != Sector.UNACTIVE) ret.add(new Point(current.x, current.y-1));
        return ret;
    }


    public static Field load(Scanner input) {
        int numRows = input.nextInt();
        int numColumns = input.nextInt();

        Sector[][] field = new Sector[numRows][numColumns];
        for (int x = 0; x < numRows; ++x) {
            for (int y = 0; y < numColumns; ++y) {
                field[x][y] = input.next().equals("w") ? Sector.WALL : Sector.FREE;
            }
        }
        return new Field(field);
    }


    public enum Sector {
        FREE,
        WALL,
        ACTIVE,
        UNACTIVE,
        CURRENTWAY,
        REALWAY
    }
}
