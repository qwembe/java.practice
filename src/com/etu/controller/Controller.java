package com.etu.controller;

import com.etu.Swing.GameLauncher;
import com.etu.model.*;
import com.etu.view.View;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Controller {

    private Model model;
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
        model.getField().setSectorActive(model.getStart());
        model.getComments().append("Добавляем стартовую клетку в OpenSet\n");
        updatelog();
        //add map

        model.setFunction_g(model.getStart(), 0);
        model.setFunction_f(model.getStart(), model.getFunction_g((model.getStart())) +
                Model.countHeuristic(model.getStart().x, model.getStart().y, model.getFinish().x, model.getFinish().y));
        while(model.isActiveFull())
        {
            Point current = model.min_f();
            if(current.equals(model.getFinish()))
            {
                model.getComments().delete(0, model.getComments().length());
                model.getComments().append("Восстанавливаем путь: \n");
                updatelog();
               restoreWay();

            }
            model.getField().setSectorUnActive(current);
            model.getComments().delete(0, model.getComments().length());
            model.getComments().append("Удаляем клетку (" + current.x + ", " + current.y + ") из OpenSet и добавляем в ClosedSet\n");
            updatelog();
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


    public void updatelog(){
        view.getLog().append(model.getComments().toString());
    }


    public void update(){
        viewUpdated();
    }

    public void load(){
        JFileChooser fc = new JFileChooser();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Data input", "dat", "txt");
        
        File f = new File("./src/com/etu/Swing/data");//"/"+ System.getProperty("user.dir") + "/src/com.etu/controller/Swing/data");
        chooser.setCurrentDirectory(f);//new File("/data/"));//"/"+ System.getProperty("user.dir") + "/src/com.etu/controller/Swing/data"));
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {



            Scanner scanner = new Scanner(GameLauncher.class.getResourceAsStream("data/" + chooser.getSelectedFile().getName()));
            this.model = Model.load(scanner);
            update();
        }




        //fc.showOpenDialog();


        //Scanner scanner = new Scanner(GameLauncher.class.getResourceAsStream("data/level2.dat"));
        //this.model = Model.load(scanner);
    }

    public void start(){
        implementAstar();
        update();
    }
    public void stop(){}
    public void resume(){}
    public void restart(){}
    public void next(){}


}
