package com.etu.controller;

import com.etu.model.*;
import com.etu.view.View;


import java.awt.*;
import java.util.Set;

public class Controller {

    private final Model model;
    private final View view;
   // private String

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public Controller(Model model)
    {
        this.model = model;
        this.view = null;
    }



    public void viewUpdated()
    {
        view.draw(model);
    }

    private void restoreWay()
    {
       Point current = model.getFinish();
       while(!current.equals(model.getStart()))
       {
           model.getField().setSectorRealWay(current);
           current = model.getFrom()[current.x][current.y];
       }


    }


    public void implementAstar() {
        double temporary_g;
        for (int i = 0; i < 7 ; i++) {
            System.out.println(" ");
        }

        double[][] test = model.getHeuristic();
        for (int i = 0; i < model.getField().getNumRows() ; i++) {
            for (int j = 0; j < model.getField().getNumColumns(); j++) {
                System.out.print((int)test[i][j] + " ");
            }
            System.out.println(" ");

        }
        model.getField().setSectorActive(model.getStart());
        //add map

        model.setFunction_g(model.getStart(), 0);
        model.setFunction_f(model.getStart(), model.getFunction_g((model.getStart())) +
                Model.countHeuristic(model.getStart().x, model.getStart().y, model.getFinish().x, model.getFinish().y));
        while(model.isActiveFull())
        {
            Point current = model.min_f();
            if(current.equals(model.getFinish()))
            {
               restoreWay();

            }
            model.getField().setSectorUnActive(current);
            Set<Point> neighbours = model.getNotUnActiveNeighbours(current);
          //  System.out.println(neighbours.size());
            for (Point neighbour: neighbours) {

                temporary_g = model.getFunction_g(current) + 1;
                if(!model.getField().isActive(neighbour) || temporary_g < model.getFunction_g(neighbour))
                {
                    model.setFrom(model.getFrom(neighbour), current);
                    model.setFunction_g(neighbour, temporary_g);
                    model.setFunction_f(neighbour, model.getFunction_g(neighbour) +
                            Model.countHeuristic(neighbour.x, neighbour.y, model.getFinish().x, model.getFinish().y));
                }
                if(!model.getField().isActive(neighbour))
                    model.getField().setSectorActive(neighbour);
            }
            neighbours.clear();

        }


    }
/*
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Model model = Model.load(input);
        Controller controller = new Controller(model);
        controller.implementAstar();
        model.getField().printWay();
    }
*/



}
