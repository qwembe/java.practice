package com.etu.model;
import java.util.Scanner;

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

    public Sector getSector(int x, int y) {

        return field[x][y];
    }

    public void setSectorActive(int x, int y)
    {
        field[x][y] = Sector.ACTIVE;
    }

    public void setSectorUnactive(int x, int y)
    {
        field[x][y] = Sector.UNACTIVE;
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
