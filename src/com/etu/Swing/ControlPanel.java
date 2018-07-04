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
    private final JComboBox box = new JComboBox(new String[] {"Start","Finish","Wall"});
    private final ButtonGroup group = new ButtonGroup();
    private final JRadioButton var1 = new JRadioButton("A-star #1", false);
    private final JRadioButton var2 = new JRadioButton("A-star #2", true);



    public ControlPanel() {
        super();
        group.add(var1);
        group.add(var2);
        add(load);
        add(start);
        add(stop);
        add(resume);
        add(restart);
        add(next);
        add(box);
        add(var1);
        add(var2);
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


    public String getBoxContents(){return box.getSelectedItem().toString();}

    public void var_1_ActionListener(ActionListener listener){var1.addActionListener(listener);}
    public void var_2_ActionListener(ActionListener listener){var2.addActionListener(listener);}

    public void loadActionListener(ActionListener listener){load.addActionListener(listener);}
    public void startActionListener(ActionListener listener){start.addActionListener(listener);}
    public void stopActionListener(ActionListener listener){stop.addActionListener(listener);}
    public void resumeActionListener(ActionListener listener){resume.addActionListener(listener);}
    public void restartActionListener(ActionListener listener){restart.addActionListener(listener);}
    public void nextActionListener(ActionListener listener){next.addActionListener(listener);}

}
