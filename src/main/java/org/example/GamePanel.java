package org.example;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3; //scale the characters by 16 * 3
    final int tileSize = originalTileSize * scale; //64x64 tile size

    final int maxScreenCol = 16;
    final int maxScreenRow = 12;

    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    int fps = 60;

    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;

    //
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {

        double drawInterval = 1000000000 / fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (keyHandler.upPressed == true) {
            playerY -= playerSpeed;
        }
        if (keyHandler.downPressed == true) {
            playerY += playerSpeed;
        }
        if (keyHandler.rightPressed == true) {
            playerX += playerSpeed;
        }
        if (keyHandler.leftPressed == true) {
            playerX -= playerSpeed;
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }

}
