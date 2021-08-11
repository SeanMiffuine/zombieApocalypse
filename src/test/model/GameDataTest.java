package model;

import exception.GameOverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameDataTest {
    private GameData game;

    @BeforeEach
    public void setUp() {
        game = new GameData();
    }

    @Test
    public void testConstruct() {
        assertEquals(800, game.getWindowWidth());
        assertEquals(580, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertTrue(game.isGameOver());
    }

    //test exception not expected
    @Test
    public void testUpdate() {
        game.setGameStart();
        try {
            game.update();
        } catch (GameOverException e) {
            fail("Not supposed to happen");
        }
        assertEquals(1, game.getRound());
        assertEquals(3, game.getEnemies().size());
        assertFalse(game.isGameOver());
    }

    //test exception expected
    @Test
    public void testUpdateGameOver() {
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.getPlayer().loseHealth();
        game.setGameOver();
        try {
            game.update();
        } catch (GameOverException e) {
            System.out.printf("Game Over expected");
        }
        assertTrue(game.isGameOver());

    }

//    @Test
//    public void testDoInputM() {
//        game.doInput("m");
//        assertEquals(1, game.getBullets().size());
//        assertEquals(2, game.getPlayer().getAmmo());
//    }
//
//    @Test
//    public void testDoInputW() {
//        game.doInput("w");
//        assertEquals(110, game.getPlayer().getPositionY());
//    }
//
//    @Test
//    public void testDoInputS() {
//        game.doInput("s");
//        assertEquals(140, game.getPlayer().getPositionY());
//    }
//
//    @Test
//    public void testDoInputA() {
//        game.doInput("a");
//        assertEquals(110, game.getPlayer().getPositionX());
//    }
//
//    @Test
//    public void testDoInputD() {
//        game.doInput("d");
//        assertEquals(140, game.getPlayer().getPositionX());
//    }
//
//    @Test
//    public void testDoInputPFalse() {
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.setGameOver();
//        game.doInput("y");
//        assertTrue(game.isGameOver());
//        assertEquals(0, game.getPlayer().getHealth());
//    }
//
//    @Test
//    public void testDoInputP() {
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.setGameOver();
//        game.doInput("l");
//        assertEquals(250, game.getWindowWidth());
//        assertEquals(250, game.getWindowHeight());
//        assertEquals(50, game.getBorder());
//        assertEquals(0, game.getRound());
//        assertEquals(0, game.getMoney());
//        assertEquals(100, game.getPlayer().getHealth());
//        assertEquals(0, game.getEnemies().size());
//        assertEquals(0, game.getBullets().size());
//        assertFalse(game.isGameOver());
//    }

    @Test
    public void testKeyPressSpace() {
        game.keyPressed(KeyEvent.VK_SPACE);
        assertEquals(game.getBullets().size(), 1);
    }

    @Test
    public void testKeyPressRestart() {
        game.keyPressed(KeyEvent.VK_W);
        assertEquals(game.getPlayer().getPositionY(), 265);
    }

    @Test
    public void testSpawn() {
        game.spawn();
        assertEquals(3, game.getEnemies().size());
    }

    @Test
    public void testGameOver() {
        game.setGameOver();
        assertTrue(game.isGameOver());
    }

    @Test
    public void testShoot() {
        game.shoot();
        assertEquals(1, game.getBullets().size());
        game.shoot();
        assertEquals(2, game.getBullets().size());
    }


    @Test
    public void testShootNoBullets() {
        game.shoot();
        game.getPlayer().shoot();
        assertEquals(1, game.getBullets().size());
        game.shoot();
        game.getPlayer().shoot();
        assertEquals(2, game.getBullets().size());
        game.shoot();
        game.getPlayer().shoot();
        game.shoot();
        game.getPlayer().shoot();
        assertEquals(4, game.getBullets().size());
    }

    @Test
    public void testNoBullets() {
        game.getPlayer().setAmmo(0);
        game.shoot();
        assertEquals(0, game.getBullets().size());
    }

    @Test
    public void testRestart() {
        game.restart();
        assertEquals(800, game.getWindowWidth());
        assertEquals(580, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());
        try {
            game.update();
            game.update();
            game.update();
        } catch (GameOverException e) {
            fail("Not supposed to fail");
        }
        game.restart();
        assertEquals(800, game.getWindowWidth());
        assertEquals(580, game.getWindowHeight());
        assertEquals(50, game.getBorder());
        assertEquals(0, game.getRound());
        assertEquals(0, game.getMoney());
        assertEquals(0, game.getEnemies().size());
        assertEquals(0, game.getBullets().size());
        assertFalse(game.isGameOver());
    }

    @Test
    public void testGetPlayer() {
        assertTrue(game.getPlayer() != null);
    }

//    @Test
//    public void testPushDataPlayer() {
//        assertTrue(game.pushDataPlayer().equals("health: " + game.getPlayer().getHealth() + " ammo: "
//                + game.getPlayer().getAmmo() + " playerX: " + game.getPlayer().getPositionX()
//                + " playerY: " + game.getPlayer().getPositionY()));
//    }

//    @Test
//    public void testPushDataGame() {
//        String enemyPositions = "";
//        String bulletPositions = "";
//
//        game.shoot();
//        game.spawn();
//        for (Enemy e : game.getEnemies()) {
//            enemyPositions += " X: " + e.getPositionX() + " Y: " + e.getPositionY() + " | ";
//        }
//        for (Bullet b : game.getBullets()) {
//            bulletPositions += " X: " + b.getPositionX() + " Y: " + b.getPositionY() + " | ";
//        }
//        assertTrue(game.pushDataGame().equals("Enemy Total: "+ game.getEnemies().size() + " Enemy Coords: " + enemyPositions
//                + "\n" + "Flying Bullets Total: " + game.getBullets().size() + " Bullet Coords:"
//                + bulletPositions));
//    }

//    @Test
//    public void testPushDataGameGameOver() {
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.getPlayer().loseHealth();
//        game.setGameOver();
//        assertTrue(game.pushDataGame().equals("Game over. P for restart."));
//    }

    @Test
    public void testPlayerGetHit() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy(400, 290));
        game.setEnemies(enemies);
        game.playerGetHit();
        assertTrue(enemies.isEmpty());
    }

    @Test
    public void testEnemyGetHit() {
        List<Enemy> enemies = new ArrayList<Enemy>();
        enemies.add(new Enemy());
        game.setEnemies(enemies);
        List<Bullet> bullets = new ArrayList<Bullet>();
        bullets.add(new Bullet(enemies.get(0).getPositionX(), enemies.get(0).getPositionY(), 1));
        bullets.add(new Bullet(125, 125, 1));
        game.setBullets(bullets);

        game.enemyGetHit();
        assertTrue(game.getEnemies().isEmpty());
        assertEquals(game.getBullets().size(), 1);
    }

    @Test
    public void testBulletOutOfBounds() {
        List<Bullet> bullets = new ArrayList<Bullet>();
        bullets.add(new Bullet(900, 900, 1));
        bullets.add(new Bullet(125, 125, 1));
        game.setBullets(bullets);

        game.bulletOutOfBounds();
        assertEquals(game.getBullets().size(), 1);
    }

    @Test
    public void testBulletUpdate() {
        game.shoot();
        game.bulletUpdate();
        assertEquals(game.getBullets().get(0).getPositionY(), 283);
    }

    @Test
    public void testGameStart() {
        game.setGameStart();
        assertFalse(game.isGameOver());
    }


}