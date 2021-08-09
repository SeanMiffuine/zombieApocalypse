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

// Examples and inspiration from previous space invader game seen in lecture.
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
//the graphics display of the game window.
public class GameWindow extends JPanel {

    private GameData game;
    private String gameOverString = "Game Over: N for New Game";

    //effects: constructs a game window to display game and sprites
    public GameWindow(GameData game) {
        setPreferredSize(new Dimension(GameData.WINDOW_WIDTH, GameData.WINDOW_HEIGHT));
        setBackground(new Color(10, 40, 30));
        this.game = game;
    }

    //modifies: this
    //effects: draws over the game window, displays game over text if game over
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGame(graphics);
        if (game.isGameOver()) {
            displayGameOver(graphics);
        }
    }

    //modifies: this
    //effects: draws each component
    private void drawGame(Graphics graphics) {
        drawPlayer(graphics);
        drawEnemies(graphics);
        drawBullets(graphics);
    }

    //modifies: this
    //effects: draws a player image on the position of player
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

    //modifies: this
    //effects: draws the list of enemies on the window
    private void drawEnemies(Graphics graphics) {
        for (Enemy enemy : game.getEnemies()) {
            drawEnemy(graphics, enemy);
        }
    }

    //modifies: this
    //effects: draws the enemy on it's coordinates
    private void drawEnemy(Graphics graphics, Enemy enemy) {
        Color graphicsColor = graphics.getColor();
        graphics.setColor(new Color(80, 100, 40));
        graphics.fillRect(enemy.getPositionX() - Enemy.SIZE / 2,
                enemy.getPositionY() - Enemy.SIZE / 2, Enemy.SIZE,
                Enemy.SIZE);
        graphics.setColor(graphicsColor);
    }

    //modifies: this
    //effects: draws the list of bullets on the window
    private void drawBullets(Graphics graphics) {
        for (Bullet bullet : game.getBullets()) {
            drawBullet(graphics, bullet);
        }
    }

    //modifies: this
    //effects: draws the bullet on it's coordinates
    private void drawBullet(Graphics graphics, Bullet bullet) {
        Color graphicsColor = graphics.getColor();
        graphics.setColor(new Color(200, 200, 40));
        graphics.fillRect(bullet.getPositionX() - Bullet.SIZE / 2,
                bullet.getPositionY() - Bullet.SIZE / 2, Bullet.SIZE,
                Bullet.SIZE);
        graphics.setColor(graphicsColor);
    }

    //modifies: this
    //effects: displays game over graphics over the game window GUI
    private void displayGameOver(Graphics graphics) {
        Color color = graphics.getColor();
        graphics.setColor(new Color(230, 230, 230));
        graphics.setFont(new Font("Consolas", 15, 30));
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int width = fontMetrics.stringWidth(gameOverString);
        graphics.drawString(gameOverString, (game.WINDOW_WIDTH - width) / 2, game.WINDOW_HEIGHT / 2);



    }
}
