package com.etu.controller;

import com.etu.model.*;
import com.etu.view.View;


import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Controller {

    private final Model model;
    private final View view;

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

    private void restoreWay(Map<Point, Point> from)
    {
        Set<Point> way = from.keySet();
        for (Point elem: way) {
            model.getField().setSectorRealWay(elem);
        }
        model.getField().setSectorRealWay(model.getStart());
        model.getField().setSectorRealWay(model.getFinish());


    }


    public void implementAstar() {
        double temporary_g;
        model.getField().setSectorActive(model.getStart());
        Map from = new HashMap<Point, Point>();
        model.setFunction_g(model.getStart(), 0);
        model.setFunction_f(model.getStart(), model.getFunction_g((model.getStart())) +
                Model.countHeuristic(model.getStart().x, model.getStart().y, model.getFinish().x, model.getFinish().y));
        while(model.isActiveFull())
        {
            Point current = model.min_f();
            if(current.equals(model.getFinish()))
            {
                restoreWay(from);
            }
            model.getField().setSectorUnActive(current);
            Set<Point> neighbours = model.getNotUnActiveNeighbours(current);
            for (Point neighbour: neighbours) {

                temporary_g = model.getFunction_g(current) + 1;
                if(!model.getField().isActive(neighbour) || temporary_g < model.getFunction_g(neighbour))
                {
                    from.put(current, neighbour);
                    model.setFunction_g(neighbour, temporary_g);
                    model.setFunction_f(neighbour, model.getFunction_g(neighbour) +
                            Model.countHeuristic(neighbour.x, neighbour.y, model.getFinish().x, model.getFinish().y));
                }
                if(!model.getField().isActive(neighbour))
                    model.getField().setSectorActive(neighbour);
            }

        }


    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Model model = Model.load(input);
        Controller controller = new Controller(model);
        controller.implementAstar();
        model.getField().printWay();
    }




}
