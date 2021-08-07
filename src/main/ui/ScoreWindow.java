package ui;

import model.GameData;

import javax.swing.*;
import java.awt.*;

public class ScoreWindow extends JPanel {
    private GameData game;
    private JLabel round;
    private JLabel ammo;
    private JLabel health;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    public ScoreWindow(GameData game) {
        this.game = game;
        setBackground(new Color(150, 150, 150));
        round = new JLabel("Rounds: " + game.getRound());
        round.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ammo = new JLabel("Ammo: " + game.getPlayer().getAmmo());
        ammo.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        health = new JLabel("Health: " + game.getPlayer().getHealth());
        health.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        add(Box.createHorizontalStrut(10));
        add(round);
        add(Box.createHorizontalStrut(10));
        add(ammo);
        add(Box.createHorizontalStrut(10));
        add(health);
    }

    public void update() {
        round.setText("Rounds: " + game.getRound());
        ammo.setText("Ammo: " + game.getPlayer().getAmmo());
        health.setText("Health: " + game.getPlayer().getHealth());
        repaint();
    }

}
