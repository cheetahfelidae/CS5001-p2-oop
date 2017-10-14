import Enemy.*;
import Tower.*;
import Tower.DataTypes.Damage;
import Tower.DataTypes.Price;
import Tower.DataTypes.WaitingStep;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final int INIT_COINS = 150;
    private static final int NUM_RATS = 70;
    private static final int NUM_ELEPHANTS = 30;
    private static final int NUM_DRAGONS = 1;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private int corridor_length;
    private int coin_balance;
    private int earned_coins;

    /**
     * Run the game until either one of enemies reach the player's territory or all enemies are killed.
     * Extended: show the number of earned coins for killing enemies for every step.
     */
    public void advance() {
        int steps = 0;
        while (true) {
            System.out.println("---------------------------------------------");
            System.out.println("Game Step #" + steps + "\tEnemies still alive are..");

            shoot_enemy(steps);
            advance_enemies();

            System.out.println("You have earned " + earned_coins + " coins so far!!");
            System.out.println("---------------------------------------------");
            System.out.println();

            if (enemies_win()) {
                System.out.println("Game is over!! The enemies have successfully managed to reach your territory..");
                break;
            } else if (all_enemies_die()) {
                System.out.println("Victory!! All enemies are killed..");
                break;
            }

            steps++;
        }

        System.out.printf("Your coins balance is %d + %d = %d coins\n", coin_balance, earned_coins, coin_balance + earned_coins);
        System.out.println("---------------------------------------------");
        System.out.println();
        coin_balance += earned_coins;
    }

    /**
     * A tower randomly shoots only one enemy (one to one).
     * A tower can hit enemy whose position is >= the position of the tower (the player's territory is at position of 0 while enemies start at position of N where N >= 0).
     * A tower shoots as soon as it is loaded, which is indicated by the will_fire() returning true.
     * Extended: The player can earn some number of coins every time they manage to kill an enemy.
     *
     * @param steps the current number of Game steps.
     */
    public void shoot_enemy(int steps) {
        Collections.shuffle(towers, new Random(System.nanoTime()));
        Collections.shuffle(enemies, new Random(System.nanoTime()));

        for (Tower tower : towers) {
            if (tower.will_fire(steps)) {

                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);

                    if (enemy.getHealth() > 0 && tower.get_position() <= enemy.getPosition()) {
                        enemy.hit(tower);

                        if (enemy.getHealth() <= 0) {
                            earned_coins += enemy.get_coins();
                        }

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
            if (enemy.getHealth() > 0) {
                enemy.advance();
                System.out.println(enemy);
            }
        }
    }

    /**
     * check if an enemy manges to reach the player's territory
     *
     * @return
     */
    public boolean enemies_win() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition() <= 0) {
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
    public void create_enemies() {
        enemies = new ArrayList<>();
        for (int i = 0; i < NUM_RATS; i++) {
            enemies.add(new Rat(corridor_length));
        }
        for (int i = 0; i < NUM_ELEPHANTS; i++) {
            enemies.add(new Elephant(corridor_length));
        }
        for (int i = 0; i < NUM_DRAGONS; i++) {
            enemies.add(new Dragon(corridor_length));
        }
    }

    /**
     *
     */
    public void create_towers() {
        System.out.println("Let's configure the towers..");
        System.out.println("There are three types of Towers you can buy..");
        System.out.printf("SlingShot: %d damage points, shooting every %d game steps, %d coins\n", Damage.SLINGSHOT.to_int(), WaitingStep.SLINGSHOT.to_int(), Price.SLINGSHOT.to_int());
        System.out.printf("Catapult: %d damage points, shooting every %d game steps, %d coins\n", Damage.CATAPULT.to_int(), WaitingStep.CATAPULT.to_int(), Price.CATAPULT.to_int());
        System.out.printf("The Wall: %d damage points, shooting every %d game steps, %d coins\n", Damage.THE_WALL.to_int(), WaitingStep.THE_WALL.to_int(), Price.THE_WALL.to_int());
        System.out.println("You now have coins of " + coin_balance);

        int num_catapult, num_slingshot, num_the_wall;
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of the Catapults, Slingshots and The Walls you want to buy respectively e.g. 5 6 3");
            System.out.print("# ");
            num_catapult = scanner.nextInt();
            num_slingshot = scanner.nextInt();
            num_the_wall = scanner.nextInt();

            if (num_catapult >= 0 && num_slingshot >= 0 && num_the_wall >= 0) {
                int sum = num_catapult * Price.CATAPULT.to_int() + num_slingshot * Price.SLINGSHOT.to_int() + num_the_wall * Price.THE_WALL.to_int();
                if (sum > coin_balance) {
                    System.out.printf("You do not have enough coins to buy these Towers, they cost %d coins, please try again!!\n", sum);
                } else {
                    coin_balance -= sum;
                    System.out.printf("Your coins balance is %d coins\n", coin_balance);
                    done = true;
                }
            } else {
                System.out.println("Your inputs are invalid, they all are supposed to be positive numbers");
            }
        } while (!done);

        towers = new ArrayList<>();
        for (int i = 0; i < num_catapult; i++) {
            towers.add(new Catapult(0));
        }
        for (int i = 0; i < num_slingshot; i++) {
            towers.add(new Slingshot(0));
        }
        for (int i = 0; i < num_the_wall; i++) {
            towers.add(new TheWall(0));
        }

    }

    /**
     * @return the current number of coins
     */
    public int get_coin_balance() {
        return coin_balance;
    }

    /**
     * @param corridor_length
     */
    public Game(int corridor_length) {
        this.corridor_length = corridor_length;
        this.coin_balance = INIT_COINS;
        this.earned_coins = 0;
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
                while (game.get_coin_balance() > 0) {
                    game.create_towers();
                    game.create_enemies();
                    game.advance();
                }
            } else {
                System.out.println("usage: corridor_length");
                System.out.println("The corridor_length argument should be a positive number");
            }
        } catch (Exception e) {
            System.out.println("usage: corridor_length");
        }
    }
}
