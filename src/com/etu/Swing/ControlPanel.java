package com.etu.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private final JButton load = createButton("Load from file");
    private final JButton start = createButton("Start");
    private final JButton stop = createButton("Stop");
    private final JButton resume = createButton("Continue");
    private final JButton restart = createButton("Restart");
    private final JButton next = createButton("Next step");

    public ControlPanel() {
        super();
        add(load);
        add(start);
        add(stop);
        add(resume);
        add(restart);
        add(next);
        setLayout(new FlowLayout());

    }

    private JButton createButton(String text){//, int x, int y) {
        JButton b = new JButton(text);
        //b.setBounds(x, y, 30, 30);
        b.setPreferredSize(new Dimension(120,25));
        //b.setFocusPainted(false);
        //b.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0));
        return b;
    }


    public void loadActionListener(ActionListener listener){load.addActionListener(listener);}
    public void startActionListener(ActionListener listener){start.addActionListener(listener);}
    public void stopActionListener(ActionListener listener){stop.addActionListener(listener);}
    public void resumeActionListener(ActionListener listener){resume.addActionListener(listener);}
    public void restartActionListener(ActionListener listener){restart.addActionListener(listener);}
    public void nextActionListener(ActionListener listener){next.addActionListener(listener);}



}
