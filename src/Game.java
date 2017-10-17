import towerdefence.Enemy;
import towerdefence.Tower;
import towerdefence.Rat;
import towerdefence.Elephant;
import towerdefence.Dragon;
import towerdefence.Catapult;
import towerdefence.Slingshot;
import towerdefence.TheWall;
import towerdefence.dataTypes.Damage;
import towerdefence.dataTypes.Price;
import towerdefence.dataTypes.WaitingStep;

import java.util.*;

/**
 * Practical 2 - OO Implementation.
 * Tower Defence Game, where the goal is to defend the player's territory by obstructing enemy attackers.
 *
 * @author Student ID: 160026335.
 */
public final class Game {
    private static final int INIT_COINS = 150;
    private static final int NUM_RATS = 70;
    private static final int NUM_ELEPHANTS = 50;
    private static final int NUM_DRAGONS = 3;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;
    private int corridor_length;
    private int coin_balance = INIT_COINS;
    private int earned_coins = 0;

    /**
     * Run the game until either one of enemies reach the player's territory or all enemies are killed.
     * Extended: show the number of earned coins for killing enemies for every step.
     */
    private void advance() {
        System.out.println("Game Starts..");

        int gameSteps = 1;
        while (true) {
            clearScreen();
            System.out.println("---------------------------------------------");
            System.out.println("Game Step #" + gameSteps + "\tEnemies still alive are..");

            shootEnemy(gameSteps);
            advanceEnemies();
            draw();

            System.out.println("You have earned " + earned_coins + " coins so far!!");
            System.out.println("---------------------------------------------");
            System.out.println();

            if (enemiesWin()) {
                System.out.println("Game is over!! The enemies have successfully managed to reach your territory..");
                break;
            } else if (allEnemiesDie()) {
                System.out.println("Victory!! All enemies are killed..");
                break;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gameSteps++;
        }

        System.out.printf("Your coins balance is %d + %d = %d coins\n", coin_balance, earned_coins, coin_balance + earned_coins);
        System.out.println("---------------------------------------------");
        System.out.println();
        coin_balance += earned_coins;
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

                    if (enemy.getHealth() > 0 && enemy.getPosition() <= tower.getPosition()) {
                        enemy.hit(tower);

                        if (enemy.getHealth() <= 0) {
                            earned_coins += enemy.getCoins();
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
    private void advanceEnemies() {
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                enemy.advance();
            }
        }
    }

    private int get_num_alive_rats() {
        int count = 0;
        for (Enemy enemy : enemies) {
            if (enemy instanceof Rat && enemy.getHealth() > 0) {
                count++;
            }
        }
        return count;
    }

    private int get_num_alive_elephants() {
        int count = 0;
        for (Enemy enemy : enemies) {
            if (enemy instanceof Elephant && enemy.getHealth() > 0) {
                count++;
            }
        }
        return count;
    }

    private int get_num_alive_dragons() {
        int count = 0;
        for (Enemy enemy : enemies) {
            if (enemy instanceof Elephant && enemy.getHealth() > 0) {
                count++;
            }
        }
        return count;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void draw() {
        int num_spaces;

        enemies.sort(Comparator.comparing(Enemy::getPosition));

        System.out.println("Rat:\t\t" + get_num_alive_rats() + " left");
        System.out.println("Elephant:\t" + get_num_alive_elephants() + " left");
        System.out.println("Dragon:\t\t" + get_num_alive_dragons() + " left");

        System.out.print(" ");
        for (int i = 0; i < corridor_length; i++) {
            System.out.print("-");
        }
        System.out.println();

        num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy instanceof Rat && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("R");
            }
        }
        System.out.printf("%"+ (corridor_length > num_spaces ? corridor_length - num_spaces : "") +"s#\n", "");

        num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy instanceof Elephant && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("E");
            }
        }
        System.out.printf("%"+ (corridor_length > num_spaces ? corridor_length - num_spaces : "") +"s#\n", "");

        num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy instanceof Dragon && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("D");
            }
        }
        System.out.printf("%"+ (corridor_length > num_spaces ? corridor_length - num_spaces : "") +"s#\n", "");

        for (int i = 0; i < corridor_length; i++) {
            System.out.print("-");
        }
        System.out.println();
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
//        for (Enemy enemy : enemies) {
//            System.out.println(enemy);
//        }
    }

    /**
     * Extended: allows a user to play this game by providing terminal-based UI to configure the towers at the start of the game, and watch the game unfold with the given tower configuration.
     */
    private void createTowers() {
        System.out.println("Let's configure the towers..");
        System.out.println("There are three types of Towers you can buy..");
        System.out.printf("\tSlingShot: %d damage points, shooting every %d game steps, %d coins\n", Damage.SLINGSHOT.getValue(), WaitingStep.SLINGSHOT.getValue(), Price.SLINGSHOT.getValue());
        System.out.printf("\tCatapult: %d damage points, shooting every %d game steps, %d coins\n", Damage.CATAPULT.getValue(), WaitingStep.CATAPULT.getValue(), Price.CATAPULT.getValue());
        System.out.printf("\tThe Wall: %d damage points, shooting every %d game steps, %d coins\n", Damage.THE_WALL.getValue(), WaitingStep.THE_WALL.getValue(), Price.THE_WALL.getValue());
        System.out.println("You now have coins of " + coin_balance);

        int num_catapult, num_slingshot, num_the_wall;
        boolean done = false;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of the Slingshots, Catapults and The Walls you want to buy respectively e.g. 5 6 3");
            System.out.print("# ");
            num_slingshot = scanner.nextInt();
            num_catapult = scanner.nextInt();
            num_the_wall = scanner.nextInt();

            if (num_slingshot >= 0 && num_catapult >= 0 && num_the_wall >= 0) {
                int sum = num_slingshot * Price.SLINGSHOT.getValue() + num_catapult * Price.CATAPULT.getValue() + num_the_wall * Price.THE_WALL.getValue();
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
        for (int i = 0; i < num_slingshot; i++) {
            towers.add(new Slingshot(corridor_length));
        }
        for (int i = 0; i < num_catapult; i++) {
            towers.add(new Catapult(corridor_length));
        }
        for (int i = 0; i < num_the_wall; i++) {
            towers.add(new TheWall(corridor_length));
        }
    }

    /**
     * Get the current number of earned coins.
     *
     * @return
     */
    private int getCoinBalance() {
        return coin_balance;
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
        int corridor_length = Integer.parseInt(args[0]);

        if (corridor_length > 0) {
            Game game = new Game(corridor_length);
//            while (game.getCoinBalance() > 0) {
//                game.createTowers();
//                game.createEnemies();
//                game.advance();
//            }
            game.createTowers();
            game.createEnemies();
            game.advance();

            System.out.println("Good Bye..");
        } else {
            System.out.println("usage: corridor_length");
            System.out.println("The corridor_length argument should be a positive number");
        }
    }
}
