package org.example;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3; //scale the characters by 16 * 4
    public final int tileSize = originalTileSize * scale; //64x64 tile size

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol=50;
    public final int maxWorldRow =50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    final int fps = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
   public CollisionChecker collisionChecker = new CollisionChecker(this);
   public Player player = new Player(this, keyHandler);


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
        delayTime();
    }

    public void delayTime() {
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
            }/*delta represents how many frames have passed so far
            by dividing (currentTime-lastTime) by draw interval we find how much many
            frames have passed by in that loop so we add that to delta, lets say in the first loop 0.3 frames pass,
            so delta becomes 0.3. Now on the next loop same thing happens until delta
            eventually adds up to 1 or more than 1. When that happens it means that a frame has
            passed and we now want to update the game so we repaint and call the update function*/
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);

        player.draw(g2);

        g2.dispose();
    }

}
