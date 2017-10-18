import towerdefence.*;
import towerdefence.dataTypes.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is mainly responsible for outputting to the terminal.
 */
public class Console {

    /**
     *
     */
    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * @param num
     */
    public static void printHyphen(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /**
     * @param num
     */
    public static void printAsterisk(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    /**
     * @param corridor_length
     * @param num_spaces
     */
    private static void printHash(int corridor_length, int num_spaces) {
        System.out.printf("%" + (corridor_length > num_spaces ? corridor_length - num_spaces : "") + "s#\n", "");
    }

    /**
     * @param num_spaces
     */
    private static void printSpaces(int num_spaces) {
        System.out.printf("%" + (num_spaces > 0 ? num_spaces : "") + "s", "");
    }

    /**
     * @param enemies
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
     * This method will
     *
     * @param enemies
     * @param corridor_length
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

    private static void showHowToPlay(int corridor_length) {
        System.out.println("You need to defend your territory (at the right end side of the corridor) from 3 types of enemies:");
        System.out.printf("- Rat:\t\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.RAT.value(), Health.RAT.value(), Coin.RAT.value());
        System.out.printf("- Elephant:\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.ELEPHANT.value(), Health.ELEPHANT.value(), Coin.ELEPHANT.value());
        System.out.printf("- Dragon:\tAdvance = %d steps\tHealth = %d points\t Earn = %d coins\n", Advance.DRAGON.value(), Health.DRAGON.value(), Coin.DRAGON.value());

        System.out.print(" ");
        printHyphen(corridor_length);

        String str = ":R (start from " + Game.NUM_RATS + " rats)";
        System.out.print(str);
        printSpaces(corridor_length - str.length());
        System.out.println(" #");
        str = ":E (start from " + Game.NUM_ELEPHANTS + " elephants)";
        System.out.print(str);
        printSpaces(corridor_length - str.length());
        System.out.println(" #");
        str = ":D (start from " + Game.NUM_DRAGONS + " dragons)";
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
     * Extended: allows a user to play this game by providing terminal-based UI to configure the towers at the start of the game, and watch the game unfold with the given tower configuration.
     *
     * @param corridor_length
     * @param account
     * @return
     */
    public static ArrayList<Tower> createTowers(int corridor_length, Account account) {
        int coin_balance = account.get_coin_balance();
        int num_catapult, num_slingshot, num_the_wall;
        boolean done = false;

        showHowToPlay(corridor_length);
        System.out.println("Your coins balance is " + coin_balance + " coins");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of the Slingshots, Catapults and The Walls <e.g. 5 6 3>");
            System.out.print("# ");
            num_slingshot = scanner.nextInt();
            num_catapult = scanner.nextInt();
            num_the_wall = scanner.nextInt();

            if (num_slingshot >= 0 && num_catapult >= 0 && num_the_wall >= 0) {
                int sum = num_slingshot * Price.SLINGSHOT.value() + num_catapult * Price.CATAPULT.value() + num_the_wall * Price.THE_WALL.value();
                if (sum > coin_balance) {
                    System.out.printf("You do not have enough coins to afford these Towers, they cost %d coins, please try again!!\n", sum);
                } else {
                    account.set_coin_balance(coin_balance - sum);
                    System.out.printf("Your coins balance is %d coins\n", coin_balance);
                    done = true;
                }
            } else {
                System.out.println("Your inputs are invalid, they all are supposed to be positive numbers");
            }
        } while (!done);

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
