import towerdefence.Enemy;
import towerdefence.Tower;
import towerdefence.Rat;
import towerdefence.Elephant;
import towerdefence.Dragon;

import java.util.*;

/**
 * Practical 2 - OO Implementation.
 * Tower Defence Game, where the goal is to defend the player's territory by obstructing enemy attackers.
 *
 * @author Student ID: 160026335.
 */
public final class Game {
    public static final int INIT_COINS = 200;
    public static final int NUM_RATS = 40;
    public static final int NUM_ELEPHANTS = 30;
    public static final int NUM_DRAGONS = 3;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private int corridor_length;
    private Account account;

    private int earned_coins;

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Run the game until either one of enemies reach the player's territory or all enemies are killed.
     * Extended: show the number of earned coins for killing enemies for every step.
     */
    private void advance() {
        final int ONE_SEC = 1000;
        System.out.println("Game will start in three seconds..");
        sleep(ONE_SEC * 3);

        int gameSteps = 1;
        while (true) {
            Console.clear_screen();
            Console.printAsterisk(corridor_length);
            System.out.println("Game Step #" + gameSteps);

            shootEnemy(gameSteps);
            advanceEnemies();
            Console.render(enemies, corridor_length);

            System.out.println("You have earned " + earned_coins + " coins so far!!");
            Console.printAsterisk(corridor_length);

            if (enemiesWin()) {
                System.out.println("Game is over!! The enemies successfully managed to reach your territory..");
                break;
            } else if (allEnemiesDie()) {
                System.out.println("Victory!! All enemies are killed..");
                break;
            }

            sleep(ONE_SEC);
            gameSteps++;
        }

        sleep(ONE_SEC * 3);
        int coin_balance = account.get_coin_balance();
        System.out.printf("Your coins balance is %d + %d = %d coins\n", coin_balance, earned_coins, coin_balance + earned_coins);
        Console.printAsterisk(corridor_length);
        System.out.println();
        System.out.println();
        System.out.println();
        account.set_coin_balance(coin_balance + earned_coins);
    }

    /**
     * A tower randomly shoots only one enemy (one to one).
     * A tower can hit enemy whose position is <= their (the player's territory are at position of 0 while enemies start at position of N where N >= 0).
     * A tower shoots as soon as it is loaded, which is indicated by the willFire() returning true.
     * Extended: The player can earn some number of coins every time they manage to kill an enemy.
     *
     * @param gameSteps the current number of game steps.
     */
    private void shootEnemy(int gameSteps) {
        Collections.shuffle(towers, new Random(System.nanoTime()));
        Collections.shuffle(enemies, new Random(System.nanoTime()));

        for (Tower tower : towers) {
            if (tower.willFire(gameSteps)) {

                for (int i = 0; i < enemies.size(); i++) {
                    Enemy enemy = enemies.get(i);

                    if (enemy.getHealth() > 0 && enemy.getPosition() <= tower.getPosition() && !enemy.isShot()) {
                        enemy.hit(tower);

                        if (enemy.getHealth() <= 0) {
                            earned_coins += enemy.getCoins();
                        }

                        enemy.setShot(true);

                        break;
                    }
                }

            }
        }

        for (Enemy enemy: enemies) {
            enemy.setShot(false);
        }
    }

    /**
     * All alive enemies advance toward the player's territory
     * Show the current position and heath points of each enemy.
     */
    private void advanceEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                enemy.advance();
            }
        }
    }

    /**
     * Check if an enemy manages to reach the player's territory.
     *
     * @return
     */
    private boolean enemiesWin() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getPosition() >= corridor_length) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if all enemies are terminated.
     *
     * @return
     */
    private boolean allEnemiesDie() {
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealth() > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Create fixed number of enemies and introduce them to the command-line terminal.
     */
    private void createEnemies() {
        enemies = new ArrayList<>();
        for (int i = 0; i < NUM_RATS; i++) {
            enemies.add(new Rat());
        }
        for (int i = 0; i < NUM_ELEPHANTS; i++) {
            enemies.add(new Elephant());
        }
        for (int i = 0; i < NUM_DRAGONS; i++) {
            enemies.add(new Dragon());
        }
    }

    public void start() {
        account = new Account(INIT_COINS);
        while (account.get_coin_balance() > 0) {
            towers = Console.createTowers(corridor_length, account);
            createEnemies();
            advance();
        }
        System.out.println("Good Bye..");
    }

    /**
     * Set the length of the corridor given from the first command-line argument.
     *
     * @param corridor_length
     */
    private Game(int corridor_length) {
        this.corridor_length = corridor_length;
    }

    /**
     * There are (2 + 1 extended) kinds of enemies, Rats (small and quick), Elephants (large and slow) and Dragon (Medium and very quick).
     *
     * @param args arg[0]: the length of the corridor.
     */
    public static void main(String[] args) {
        try {
            int corridor_length = Integer.parseInt(args[0]);

            if (corridor_length > 0) {
                new Game(corridor_length).start();
            } else {
                System.out.println("usage: corridor_length");
                System.out.println("The corridor_length argument should be more than 50");
            }
        } catch (Exception e) {
            System.out.println("usage: corridor_length");
        }
    }
}
