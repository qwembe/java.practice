package com.etu;

import javax.swing.*;
import java.awt.*;

public class GameLauncher extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameLauncher("A-Star Algorithm").setVisible(true));
    }

    private final Component canvas;
    private final ControlPanel toolBar;
    private final Component log;

    public GameLauncher(String title) throws HeadlessException {
        super(title);

        canvas = new JPanel();
        canvas.setPreferredSize(new Dimension(100,100));

        toolBar = new JFrame();

        log = new JPanel();
        log.setPreferredSize(new Dimension(50,50));

        J




    }
}
