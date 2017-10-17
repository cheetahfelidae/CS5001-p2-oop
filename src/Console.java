import towerdefence.*;
import towerdefence.dataTypes.Damage;
import towerdefence.dataTypes.Price;
import towerdefence.dataTypes.WaitingStep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class is mainly responsible for outputting to the terminal.
 */
public class Console {

    public static void clear_screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printHyphen(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void printAsterisk(int num) {
        for (int i = 0; i < num; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    private static void printSpaces(int corridor_length, int num_spaces) {
        if (corridor_length > num_spaces) {
            System.out.printf("%" + (corridor_length - num_spaces) + "s#\n", "");
        }
    }

    private static void printSpaces(int num_spaces) {
        if (num_spaces > 0) {
            System.out.printf("%" + num_spaces + "s#\n", "");
        }
    }

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

        System.out.println("Rat:\t\t" + num_rats + " left");
        System.out.println("Elephant:\t" + num_elephants + " left");
        System.out.println("Dragon:\t\t" + num_dragons + " left");
    }


    public static void draw(ArrayList<Enemy> enemies, int corridor_length) {
        enemies.sort(Comparator.comparing(Enemy::getPosition));

        showAliveEnemies(enemies);

        System.out.print(" ");
        printHyphen(corridor_length);

        // Rats line
        int num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy.getPosition() <= corridor_length && enemy instanceof Rat && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("R");
            }
        }
        printSpaces(corridor_length, num_spaces);

        // Elephants line
        num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy.getPosition() <= corridor_length && enemy instanceof Elephant && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("E");
            }
        }
        printSpaces(corridor_length, num_spaces);

        // Dragons line
        num_spaces = 0;
        System.out.print("|");
        for (Enemy enemy : enemies) {
            if (num_spaces < enemy.getPosition() && enemy.getPosition() <= corridor_length && enemy instanceof Dragon && enemy.getHealth() > 0) {
                num_spaces = enemy.getPosition();
                if (num_spaces > 1) {
                    System.out.printf("%" + (num_spaces - 1) + "s", "");
                }
                System.out.print("D");
            }
        }
        printSpaces(corridor_length, num_spaces);

        System.out.print(" ");
        printHyphen(corridor_length);
    }

    private static void createBanner(int coin_balance) {
        System.out.println("3 types of Towers you can purchase..");
        System.out.printf("- SlingShot:\tDamage = %d points\tReload = %d game steps\t Earn = %d coins\n", Damage.SLINGSHOT.getValue(), WaitingStep.SLINGSHOT.getValue(), Price.SLINGSHOT.getValue());
        System.out.printf("- Catapult:\tDamage = %d points\tReload = %d game steps\t Earn = %d coins\n", Damage.CATAPULT.getValue(), WaitingStep.CATAPULT.getValue(), Price.CATAPULT.getValue());
        System.out.printf("- The Wall:\tDamage = %d points\tReload = %d game steps\t Earn = %d coins\n", Damage.THE_WALL.getValue(), WaitingStep.THE_WALL.getValue(), Price.THE_WALL.getValue());
        System.out.println("You now have coins of " + coin_balance);
    }

    /**
     * Extended: allows a user to play this game by providing terminal-based UI to configure the towers at the start of the game, and watch the game unfold with the given tower configuration.
     */
    public static ArrayList<Tower> createTowers(int corridor_length, Account account) {
        int coin_balance = account.get_coin_balance();
        int num_catapult, num_slingshot, num_the_wall;
        boolean done = false;

        createBanner(coin_balance);

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Enter the number of the Slingshots, Catapults and The Walls <e.g. 5 6 3>");
            System.out.print("# ");
            num_slingshot = scanner.nextInt();
            num_catapult = scanner.nextInt();
            num_the_wall = scanner.nextInt();

            if (num_slingshot >= 0 && num_catapult >= 0 && num_the_wall >= 0) {
                int sum = num_slingshot * Price.SLINGSHOT.getValue() + num_catapult * Price.CATAPULT.getValue() + num_the_wall * Price.THE_WALL.getValue();
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
