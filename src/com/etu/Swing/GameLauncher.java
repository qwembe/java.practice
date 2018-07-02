package com.etu.Swing;

import com.etu.controller.Controller;
import com.etu.model.Model;
import com.etu.view.View;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class GameLauncher extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameLauncher("A-Star Algorithm").setVisible(true));
    }

    private final JPanel canvas;
    private final ControlPanel toolBar;
    private final Component log;

    public GameLauncher(String title) throws HeadlessException {
        super(title);

        canvas = new JPanel();
        canvas.setLayout(new OverlayLayout(canvas));
        canvas.setPreferredSize(new Dimension(500, 500));

        toolBar = new ControlPanel();
        toolBar.setPreferredSize(new Dimension(700, 75));

        log = new JPanel();
        log.setPreferredSize(new Dimension(50, 50));

        JPanel rootPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        /*
            next 3 command sets tree components on jframe
            where c is param
            gbl - my GridBagLayot
        */

        toolBarConfig(c);
        gbl.setConstraints(toolBar,c);
        rootPanel.add(toolBar);


        canvasConfig(c);
        gbl.setConstraints(canvas,c);
        rootPanel.add(canvas,c);

        logConfig(c);
        gbl.setConstraints(log,c);
        rootPanel.add(log,c);

        rootPanel.setLayout(gbl);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);


        initListeners();
    }

    private void initListeners(){

        Scanner scanner = new Scanner(GameLauncher.class.getResourceAsStream("data/level1.dat"));
        Model model = Model.load(scanner);
        View view = new View();
        Controller controller = new Controller(model,view);

        view.setGraphics(new SwingGraphicsAdapter(canvas.getGraphics()));



        Timer timer = new Timer(50, e -> {
            controller.viewUpdated();
            canvas.requestFocus();
        });
        timer.setRepeats(true);
        timer.start();
    }

    private void toolBarConfig(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.VERTICAL;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(25, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 1;
    }

    private void canvasConfig(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.BOTH;
        c.gridheight = 3;
        c.gridwidth  = 3;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
    }

    private void logConfig(GridBagConstraints c) {
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.BOTH;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = GridBagConstraints.RELATIVE;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
    }

}