package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;
import towerdefence.dataTypes.WaitingStep;

/**
 * Elephant is the slowest among the other types of enemies but is much hard to be killed.
 */
public class Elephant extends Enemy {
    private int gameSteps = 1;
    /**
     * Elephants advances by 1 position every 2 game steps.
     */
    public void advance() {
        if (gameSteps % WaitingStep.ELEPHANT.value() == 0) {
            position += Advance.ELEPHANT.value();
        }
        gameSteps++;
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     *
     * @return the number of coins.
     */
    public int getCoins() {
        return Coin.ELEPHANT.value();
    }

    /**
     * Initialise Elephant's health points.
     */
    public Elephant() {
        health = Health.ELEPHANT.value();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return the name of the class, position and health points of the Enemy.
     */
    public String toString() {
        return "ELEPHANT:\t" + super.toString();
    }
}
