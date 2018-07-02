package com.etu;

import javax.swing.*;
import java.awt.*;

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
        toolBar.setPreferredSize(new Dimension(700, 100));

        log = new JPanel();
        log.setPreferredSize(new Dimension(50, 50));

        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        toolBarConfig(c);
        rootPanel.add(toolBar, c);

        canvasConfig(c);
        rootPanel.add(canvas,c);

        logConfig(c);
        rootPanel.add(log,c);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        setLocationRelativeTo(null);


    }

    private void initListeners(){

        /*
        Controller controller = new Controller();



        Timer timer = new Timer(50, e -> {
            controller.viewUpdated();
            canvas.requestFocus();
        });
        timer.setRepeats(true);
        timer.start();*/
    }

    private void toolBarConfig(GridBagConstraints c) {
        c.fill = GridBagConstraints.BOTH;
        c.gridx = GridBagConstraints.NORTH;
        c.gridy = GridBagConstraints.WEST;
        c.gridheight = 1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.CENTER;

    }

    private void canvasConfig(GridBagConstraints c) {
        c.fill = GridBagConstraints.BOTH;
        c.gridx = GridBagConstraints.NORTH;
        c.gridy = GridBagConstraints.WEST;
        c.gridheight = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
    }

    private void logConfig(GridBagConstraints c) {
        c.fill = GridBagConstraints.BOTH;
        c.gridx = GridBagConstraints.NORTH;
        c.gridy = GridBagConstraints.WEST;
        c.gridheight = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.CENTER;
    }

}