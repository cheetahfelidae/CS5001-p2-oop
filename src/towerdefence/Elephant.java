package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;
import towerdefence.dataTypes.WaitingStep;

public class Elephant extends Enemy {
    /**
     * Elephants advances by 1 position every 2 game steps
     */
    public void advance() {
        if (gameSteps % WaitingStep.ELEPHANT.to_int() == 0) {
            position += Advance.ELEPHANT.to_int();
        }
        gameSteps++;
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     * @return the number of coins.
     */
    public int getCoins() {
        return Coin.ELEPHANT.to_int();
    }

    /**
     * Initialise Elephant's health points.
     */
    public Elephant() {
        health = Health.ELEPHANT.to_int();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return the name of the class, position and health points of the Enemy.
     */
    public String toString() {
        return "ELEPHANT:\t" + super.toString();
    }
}
