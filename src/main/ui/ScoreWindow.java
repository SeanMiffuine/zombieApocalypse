package ui;

import model.GameData;

import javax.swing.*;
import java.awt.*;

// Examples and inspiration from previous space invader game seen in lecture
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase
//displays data on the GUI relating to score; health, ammo, zombies currently, round level, bullets on board.
public class ScoreWindow extends JPanel {
    private GameData game;
    private JLabel round;
    private JLabel ammo;
    private JLabel health;
    private JLabel flyingBullets;
    private JLabel enemiesLeft;
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    //effects: constructs a score window
    public ScoreWindow(GameData game) {
        this.game = game;
        setBackground(new Color(150, 150, 150));
        round = new JLabel("Rounds: " + game.getRound());
        round.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ammo = new JLabel("Ammo: " + game.getPlayer().getAmmo());
        ammo.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        health = new JLabel("Health: " + game.getPlayer().getHealth());
        health.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        flyingBullets = new JLabel("Bullets flying: " + game.getBullets().size());
        flyingBullets.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        enemiesLeft = new JLabel("Enemies left: " + game.getEnemies().size());
        enemiesLeft.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        add(Box.createHorizontalStrut(5));
        add(round);
        add(Box.createHorizontalStrut(5));
        add(ammo);
        add(Box.createHorizontalStrut(5));
        add(health);
        add(Box.createHorizontalStrut(5));
        add(flyingBullets);
        add(Box.createHorizontalStrut(5));
        add(enemiesLeft);

    }

    //modifies: this
    //effects: updates the score window to match data.
    public void update() {
        round.setText("Rounds: " + game.getRound());
        ammo.setText("Ammo: " + game.getPlayer().getAmmo());
        health.setText("Health: " + game.getPlayer().getHealth());
        flyingBullets.setText("Bullets flying: " + game.getBullets().size());
        enemiesLeft.setText("Enemies left: " + game.getEnemies().size());

        repaint();
    }

}
