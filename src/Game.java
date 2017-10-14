import Enemy.*;
import Tower.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {
    private static final int INIT_COINS = 100;
    private static final int NUM_RATS = 70;
    private static final int NUM_ELEPHANTS = 30;
    private static final int NUM_DRAGONS = 1;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private int corridor_length;
    private int cur_coins;

    /**
     * Run game steps until one of enemies reach the player's territory or all enemies are killed.
     */
    public void advance() {
        int steps = 0;
        // steps
        while (true) {
            System.out.println("---------------------------------------------");
            System.out.println("Step: " + steps);

            shoot_enemy(steps);
            advance_enemies();

            if (enemies_win()) {
                System.out.println("Game is over!! The enemies have successfully managed to reach your territory..");
                break;
            }
            if (all_enemies_die()) {
                System.out.println("Victory!! All enemies are killed..");
                break;
            }

            steps++;
        }
    }

    /**
     * Each tower shoots each enemy (one to one).
     * A tower can hit enemy whose position is >= the position of the tower (the player's territory is at position of 0 while enemies start at position of N where N >= 0).
     * A tower shoots as soon as it is loaded, which is indicated by the will_fire() returning true.
     * @param steps Game step.
     */
    public void shoot_enemy(int steps) {
        for (Tower tower: towers) {
            if (tower.will_fire(steps)) {
                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);
                    if (!enemy.dead() && tower.get_position() <= enemy.getPosition()) {
                        enemy.hit(tower);
                        break;
                    }
                }
            }
        }
    }

    /**
     * All alive enemies advance toward the player's territory
     * Show the current position and heath points of each enemy.
     */
    public void advance_enemies() {
        for (Enemy enemy : enemies) {
            if (!enemy.dead()) {
                enemy.advance();
                System.out.println(enemy);
            }
        }
        System.out.println("---------------------------------------------");
        System.out.println();
    }

    /**
     * check if an enemy manges to reach the player's territory
     *
     * @return
     */
    public boolean enemies_win() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition() < 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * check if all of enemies are terminated
     *
     * @return
     */
    public boolean all_enemies_die() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() > 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Create enemies in random order
     */
    public void create_chars() {
        enemies = new ArrayList<>();
        for(int i = 0; i < NUM_RATS; i++) {
            enemies.add(new Rat(corridor_length));
        }
        for(int i = 0; i < NUM_ELEPHANTS; i++) {
            enemies.add(new Elephant(corridor_length));
        }
        for(int i = 0; i < NUM_DRAGONS; i++) {
            enemies.add(new Dragon(corridor_length));
        }
        Collections.shuffle(enemies, new Random(System.nanoTime()));

        towers = new ArrayList<>();
        towers.add(new Catapult(0));
        towers.add(new Catapult(0));
        towers.add(new Catapult(0));
        towers.add(new Catapult(0));
        towers.add(new Catapult(0));
        towers.add(new Slingshot(0));
        towers.add(new Slingshot(0));
        towers.add(new Slingshot(0));
        towers.add(new Slingshot(0));
        towers.add(new Slingshot(0));
        towers.add(new Slingshot(0));
        towers.add(new TheWall(0));
        towers.add(new TheWall(0));
        towers.add(new TheWall(0));
    }

    /**
     * @param corridor_length
     */
    public Game(int corridor_length) {
        this.corridor_length = corridor_length;
        this.cur_coins = INIT_COINS;
    }

    /**
     * There are 2 kinds of enemies, Rats (small and quick) and Elephants (large and slow)
     *
     * @param args arg[0]: the length of the corridor
     */
    public static void main(String[] args) {
        try {
            int corridor_length = Integer.parseInt(args[0]);

            Game game;

            if (corridor_length > 0) {
                game = new Game(corridor_length);
                game.create_chars();
                game.advance();
            } else {
                System.out.println("usage: corridor_length");
            }
        } catch (Exception e) {
            System.out.println("usage: corridor_length");
        }
    }
}
