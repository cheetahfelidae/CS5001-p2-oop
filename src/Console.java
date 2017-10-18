import towerdefence.Dragon;
import towerdefence.Elephant;
import towerdefence.Enemy;
import towerdefence.Rat;
import towerdefence.Tower;
import towerdefence.Slingshot;
import towerdefence.Catapult;
import towerdefence.TheWall;
import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Damage;
import towerdefence.dataTypes.Health;
import towerdefence.dataTypes.Price;
import towerdefence.dataTypes.WaitingStep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is mainly responsible for outputting to the terminal.
 */
public class Console {

    /**
     * This method is used to clean screen to be able to render game.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Print a specific number of '-'.
     *
     * @param num the desired number of hyphen to be printed
     */
    public static void printHyphen(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * Print a specific number of '*'.
     *
     * @param num the desired number of asterisks to be printed
     */
    public static void printAsterisk(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * Print hash which represents the player's territory.
     *
     * @param corridor_length
     * @param num_spaces
     */
    private static void printHash(int corridor_length, int num_spaces) {
        System.out.printf("%" + (corridor_length > num_spaces ? corridor_length - num_spaces : "") + "s#\n", "");
    }

    /**
     * Print a specific number of spaces.
     *
     * @param num_spaces
     */
    private static void printSpaces(int num_spaces) {
        System.out.printf("%" + (num_spaces > 0 ? num_spaces : "") + "s", "");
    }

    /**
     * This is used for each game steps to check the number of serving enemies.
     *
     * @param enemies the set of enemies of the game.
     */
    private static void showAliveEnemies(ArrayList<Enemy> enemies) {
        int num_rats = 0, num_elephants = 0, num_dragons = 0;
        for (Enemy enemy : enemies) {
            if (enemy.getHealth() > 0) {
                if (enemy instanceof Rat) {
                    num_rats++;
                } else if (enemy instanceof Elephant) {
                    num_elephants++;
                } else if (enemy instanceof Dragon) {
                    num_dragons++;
                }
            }
        }

        System.out.println("R = " + num_rats + " surviving rats");
        System.out.println("E = " + num_elephants + " surviving elephants");
        System.out.println("D = " + num_dragons + " surviving dragons");
    }

    /**
     * Render game for each game steps with simple graphic, indicating state of the enemies and a current position of the enemies.
     *
     * @param enemies         the set of enemies of the game.
     * @param corridor_length the length of the corridor specified by the first command-line argument.
     */
    public static void render(ArrayList<Enemy> enemies, int corridor_length) {
        enemies.sort(Comparator.comparing(Enemy::getPosition));

        showAliveEnemies(enemies);

        System.out.print(" ");
        printHyphen(corridor_length);

        // Rats line
        int num_occupied_spaces = 0;
        System.out.print(":");
        for (Enemy enemy : enemies) {
            if (num_occupied_spaces < enemy.getPosition() && enemy.getHealth() > 0 && enemy instanceof Rat) {
                num_occupied_spaces = enemy.getPosition();
                printSpaces(num_occupied_spaces - 1);
                System.out.print("R");
            }
        }
        printHash(corridor_length, num_occupied_spaces);

        // Elephants line
        num_occupied_spaces = 0;
        System.out.print(":");
        for (Enemy enemy : enemies) {
            if (num_occupied_spaces < enemy.getPosition() && enemy.getHealth() > 0 && enemy instanceof Elephant) {
                num_occupied_spaces = enemy.getPosition();
                printSpaces(num_occupied_spaces - 1);
                System.out.print("E");
            }
        }
        printHash(corridor_length, num_occupied_spaces);

        // Dragons line
        num_occupied_spaces = 0;
        System.out.print(":");
        for (Enemy enemy : enemies) {
            if (num_occupied_spaces < enemy.getPosition() && enemy.getHealth() > 0 && enemy instanceof Dragon) {
                num_occupied_spaces = enemy.getPosition();
                printSpaces(num_occupied_spaces - 1);
                System.out.print("D");
            }
        }
        printHash(corridor_length, num_occupied_spaces);

        System.out.print(" ");
        printHyphen(corridor_length);
    }

    /**
     * Guide a user how to play the game; what they can purchase to defend their territory, how many enemies there are in the game and what types of enemies they will encounter with.
     *
     * @param corridor_length the length of the corridor specified by the first command-line argument.
     */
    private static void showHowToPlay(int corridor_length) {
        System.out.println("You need to defend your territory (at the right end side of the corridor) from 3 types of enemies:");
        System.out.printf("- Rat:\t\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.RAT.value(), Health.RAT.value(), Coin.RAT.value());
        System.out.printf("- Elephant:\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.ELEPHANT.value(), Health.ELEPHANT.value(), Coin.ELEPHANT.value());
        System.out.printf("- Dragon:\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.DRAGON.value(), Health.DRAGON.value(), Coin.DRAGON.value());

        System.out.print(" ");
        printHyphen(corridor_length);

        String str = ":R (initialise from " + Game.NUM_RATS + " rats)";
        System.out.print(str);
        printSpaces(corridor_length - str.length());
        System.out.println(" #");
        str = ":E (initialise from " + Game.NUM_ELEPHANTS + " elephants)";
        System.out.print(str);
        printSpaces(corridor_length - str.length());
        System.out.println(" #");
        str = ":D (initialise from " + Game.NUM_DRAGONS + " dragons)";
        System.out.print(str);
        printSpaces(corridor_length - str.length());
        System.out.println(" #");

        System.out.print(" ");
        printHyphen(corridor_length);

        System.out.println("There are 3 types of towers you can purchase:");
        System.out.printf("- SlingShot:\tDamage = %d points\tReload = %d every game steps\t Cost = %d coins\n", Damage.SLINGSHOT.value(), WaitingStep.SLINGSHOT.value(), Price.SLINGSHOT.value());
        System.out.printf("- Catapult:\tDamage = %d points\tReload = %d every game steps\t Cost = %d coins\n", Damage.CATAPULT.value(), WaitingStep.CATAPULT.value(), Price.CATAPULT.value());
        System.out.printf("- The Wall:\tDamage = %d points\tReload = %d every game steps\t Cost = %d coins\n", Damage.THE_WALL.value(), WaitingStep.THE_WALL.value(), Price.THE_WALL.value());
    }

    /**
     * Extended: allows a user to play this game by providing terminal-based UI to configure the towers at the initialise of the game, and watch the game unfold with the given tower configuration.
     *
     * @param corridor_length the length of the corridor specified by the first command-line argument.
     * @param account         the player's coin account.
     * @return the purchased set of towers.
     */
    public static ArrayList<Tower> createTowers(int corridor_length, Account account) {
        int coin_balance = account.getCoinBalance();
        int num_catapult, num_slingshot, num_the_wall;
        boolean done = false;

        showHowToPlay(corridor_length);
        System.out.println("Your coins balance is " + coin_balance + " coins");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of the Slingshots, Catapults and The Walls <e.g. 5 6 3> Exit the game type <0 0 0>");
            System.out.print("# ");
            num_slingshot = scanner.nextInt();
            num_catapult = scanner.nextInt();
            num_the_wall = scanner.nextInt();

            if (num_slingshot > 0 && num_catapult > 0 && num_the_wall > 0) {
                int sum = num_slingshot * Price.SLINGSHOT.value() + num_catapult * Price.CATAPULT.value() + num_the_wall * Price.THE_WALL.value();
                if (sum > coin_balance) {
                    System.out.printf("You do not have enough coins to afford these Towers, they cost %d coins, please try again!!\n", sum);
                } else {
                    account.setCoinBalance(coin_balance - sum);
                    System.out.printf("Your coins balance is %d coins\n", coin_balance);
                    done = true;
                }
            } else if (num_slingshot + num_catapult + num_the_wall == 0) {
                done = true;
            } else {
                System.out.println("Your inputs are invalid, they all are supposed to be positive numbers");
            }
        } while (!done);

        // create towers.
        ArrayList<Tower> towers = new ArrayList<>();
        for (int i = 0; i < num_slingshot; i++) {
            towers.add(new Slingshot(corridor_length));
        }
        for (int i = 0; i < num_catapult; i++) {
            towers.add(new Catapult(corridor_length));
        }
        for (int i = 0; i < num_the_wall; i++) {
            towers.add(new TheWall(corridor_length));
        }
        return towers;
    }
}
