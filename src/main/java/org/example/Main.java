package org.example;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("UNIBZ 2d Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null); // display at center of the screen
        window.setVisible(true);

    }
}