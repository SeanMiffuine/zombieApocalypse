package ui;

import com.sun.org.apache.xpath.internal.operations.Gt;
import model.Bullet;
import model.Enemy;
import model.GameData;
import model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameWindow extends JPanel {

    private GameData game;

    public GameWindow(GameData game) {
        setPreferredSize(new Dimension(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT));
        setBackground(Color.BLACK);
        this.game = game;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGame(graphics);
        //if (game.isGameOver()) {
        //    displayGameOver(graphics);
        //}
    }

    private void drawGame(Graphics graphics) {
        drawPlayer(graphics);
        drawEnemies(graphics);
        drawBullets(graphics);
    }

    //???
    private void drawPlayer(Graphics graphics) {
        Player player = game.getPlayer();
        Color graphicsColor = graphics.getColor();
        //graphics.setColor(new Color(120, 100, 180));
        //graphics.fillRect(player.getPositionX() - player.getSize() / 2,
        //        player.getPositionY() - player.getSize() / 2, player.getSize(),
        //        player.getSize());
        graphics.setColor(graphicsColor);
        try {
            graphics.drawImage((ImageIO.read(new File("data/shoota.png"))),
                    player.getPositionX() - player.getSize() / 2,
                    player.getPositionY() - player.getSize() / 2, null);
        } catch (IOException e) {
            System.out.println("no file");
        }
    }

    private void drawEnemies(Graphics graphics) {
        for (Enemy enemy : game.getEnemies()) {
            drawEnemy(graphics, enemy);
        }
    }

    private void drawEnemy(Graphics graphics, Enemy enemy) {
        Color graphicsColor = graphics.getColor();
        graphics.setColor(new Color(80, 100, 40));
        graphics.fillRect(enemy.getPositionX() - Enemy.SIZE / 2,
                enemy.getPositionY() - Enemy.SIZE / 2, Enemy.SIZE,
                Enemy.SIZE);
        graphics.setColor(graphicsColor);
    }

    private void drawBullets(Graphics graphics) {
        for (Bullet bullet : game.getBullets()) {
            drawBullet(graphics, bullet);
        }
    }

    private void drawBullet(Graphics graphics, Bullet bullet) {
        Color graphicsColor = graphics.getColor();
        graphics.setColor(new Color(80, 100, 40));
        graphics.fillRect(bullet.getPositionX() - Bullet.SIZE / 2,
                bullet.getPositionY() - Bullet.SIZE / 2, Bullet.SIZE,
                Bullet.SIZE);
        graphics.setColor(graphicsColor);
    }

    //private void displayGameOver(Graphics graphics) {
    //
    //}
}
