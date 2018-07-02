package com.etu.controller;

import com.etu.model.Model;
import com.etu.view.View;

import java.util.HashMap;
import java.util.Map;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void viewUpdated()
    {
        view.draw(model);
    }

    public void implementAstar() {

        model.getField().setSectorActive(model.getStart().x, model.getFinish().y);
        Map from = new HashMap<>();


    }




}
