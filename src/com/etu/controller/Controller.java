package com.etu.controller;

import com.etu.Swing.GameLauncher;
import com.etu.model.*;
import com.etu.view.View;


import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class Controller {

    private Model model;
    private final View view;
    private Thread thr;
    private Timer timer;


    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        this.thr = new Thread();
        this.timer = new Timer(0,null);
    }

    public Controller(Model model) {
        this.model = model;
        this.view = null;
    }


    public void addCommentToLog(String appender) {
        model.getComments().delete(0, model.getComments().length());
        model.getComments().append(appender);
        updateLog();
    }
/*
    public synchronized void waiter() throws InterruptedException {
        thr.wait();
    }

    public synchronized void notifier()
    {
        notifyAll();
    }
*/
    public void viewUpdated() {
        view.draw(model);
    }

    private void restoreWay() {
        Point current = model.getFinish();
        //  model.getField().setSectorRealWay(model.getStart());
        while (!current.equals(model.getStart())) {
            model.getField().setSectorRealWay(current);
            addCommentToLog("Путь проходит через клетку (" + current.x + ", " + current.y + ")\n");
            current = model.getFrom()[current.x][current.y];
        }
        addCommentToLog("Путь начался из клетки (" + model.getStart().x + ", " + model.getStart().y + ")\n");


    }

    private void restoreWay(Point current)
    {
       // current = model.getFrom()[current.x][current.y];
        while (!current.equals(model.getStart())) {
            model.getField().setSectorCurrent(current);
            current = model.getFrom()[current.x][current.y];
        }

    }
   // private void makeFree(Point curr)
    //{
     //   Point
   // }


//    public void implementAstar() {
    public synchronized void implementAstar() throws InterruptedException {
        double temporary_g;
        model.getField().setSectorActive(model.getStart());
        addCommentToLog("Добавляем стартовую клетку в OpenSet\n\n");
        model.setFunction_g(model.getStart(), 0);
        addCommentToLog("g(start) = 0\n\n");
        model.setFunction_f(model.getStart(), model.getFunction_g((model.getStart())) +
                Model.countHeuristic(model.getStart().x, model.getStart().y, model.getFinish().x, model.getFinish().y));
        addCommentToLog("f(start) = g(start) + h(start) = " + model.getFunction_f(model.getStart()) + "\n\n");
        while (model.isActiveFull()) {
            Point current = model.min_f();
            addCommentToLog("Выбираем точку (" + current.x + ", " + current.y + ") из OpenSet, так как значение функции f(current) для нее минимально\n\n");
            wait();
            model.getField().setSectorCurrent(current);
            wait();
            if (current.equals(model.getFinish())) {
                addCommentToLog("Восстанавливаем путь: \n\n");
                restoreWay();
                return;

            }
            model.getField().setSectorUnActive(current);
            addCommentToLog("Удаляем клетку (" + current.x + ", " + current.y + ") из OpenSet и добавляем в ClosedSet\n\n");
            Set<Point> neighbours = model.getNotUnActiveNeighbours(current);
            //  System.out.println(neighbours.size());
            addCommentToLog("Рассматриваем всех Unclosed соседей для точки (" + current.x + ", " + current.y + "): \n\n");
            for (Point neighbour : neighbours) {
                addCommentToLog("Рассматриваем клетку (" + neighbour.x + ", " + neighbour.y + "):\n\n");
                temporary_g = model.getFunction_g(current) + 1;
                addCommentToLog("Промежуточное значение функции g для этой точки = " + temporary_g + "\n\n");
                if (!model.getField().isActive(neighbour) || temporary_g < model.getFunction_g(neighbour)) {
                    if (!model.getField().isActive(neighbour))
                        addCommentToLog("Так как клетка (" + neighbour.x + ", " + neighbour.y + ") не находится в OpenSet, то ");
                    else if (temporary_g < model.getFunction_g(neighbour))
                        addCommentToLog("Так как значение функции g для клетки (" + neighbour.x + ", " + neighbour.y + ") меньше промежуточного, то ");
                    model.setFrom(model.getFrom(neighbour), current);
                    restoreWay(current);
                    addCommentToLog("устанавливаем, что мы пришли в эту клетку из клетки (" + current.x + ", " + current.y + ")\n\n");
                    model.setFunction_g(neighbour, temporary_g);
                    addCommentToLog("Устанавливаем значение функции g от нее равным промежуточному значению. " +
                            "Теперь функция g от клетки (" + neighbour.x + ", " + neighbour.y + ") равна " + model.getFunction_g(neighbour) + "\n\n");
                    model.setFunction_f(neighbour, model.getFunction_g(neighbour) +
                            Model.countHeuristic(neighbour.x, neighbour.y, model.getFinish().x, model.getFinish().y));
                    addCommentToLog("Устанавливаем значение функции f для этой клетки, как сумму значений функции g и эвристической функции." +
                            " Теперь значение функции f от клетки (" + neighbour.x + ", " + neighbour.y + ") равно " + model.getFunction_f(neighbour) + "\n\n");
                }
                if (!model.getField().isActive(neighbour)) {
                    model.getField().setSectorActive(neighbour);
                    addCommentToLog("Добавляем клетку (" + neighbour.x + ", " + neighbour.y + ") в OpenSet\n\n");
                }
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

    private void clearLog(){
        view.getLog().setText("");
    }

    private void updateLog() {
        view.getLog().append(model.getComments().toString());
    }

    public void update() {
        viewUpdated();
    }

    public void clearModel(){
        model.clear();
        thr.interrupt();
    }

    public void startTimer() {
        try {
            timer.start();
        } catch (Exception e){

        }
        update();
    }

    public void stopTimer() {
        try {
            timer.stop();
        } catch (Exception e){

        }
        update();
    }

    public void resume(){
        startTimer();
        update();
    }

    public void restart(){
        model = Model.load();
        clearLog();
        stopTimer();
        try {
            thr.interrupt();
        } catch (Exception e){

        }
//        thr = null;
        update();
    }

    public synchronized void next(){
        notifyAll();
        update();
    }

    public void load() {

        restart();
        JFileChooser fc = new JFileChooser();
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Data input", "dat", "txt");

        File f = new File("./src/com/etu/Swing/data");//"/"+ System.getProperty("user.dir") + "/src/com.etu/controller/Swing/data");
        chooser.setCurrentDirectory(f);//new File("/data/"));//"/"+ System.getProperty("user.dir") + "/src/com.etu/controller/Swing/data"));
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {


            Scanner scanner = new Scanner(GameLauncher.class.getResourceAsStream("data/" + chooser.getSelectedFile().getName()));
            this.model = Model.load(scanner);
            update();
        }
    }
    public void start(){

        clearLog();
        clearModel();
        thr = new Thread(() -> {
            try {
                implementAstar();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

       try {
           thr.start();
       } catch (IllegalThreadStateException e){
            return;
       }

       timer = new Timer(500, e -> {
           next();
           viewUpdated();
       });

        startTimer();
        update();
    }

    public void mouseClick(MouseEvent e, String boxContents){
        if(timer.isRunning()) {
            stopTimer();
            clearModel();
            update();
            return;
        }
        clearModel();
        Point cords = view.getCoords(e.getPoint());
        switch (boxContents){
            case "Start": model.setStart(cords);break;
            case "Finish": model.setFinish(cords);break;
            case "Wall":
                if (model.isWall(cords)) model.setEmpty(cords);
                else model.setWall(cords);
                break;
        }
        update();
    }




}


