package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

/**
 * Rat is quicker than Elephant but far more slower than Dragon and is easiest to be killed.
 */
public class Rat extends Enemy {
    /**
     * Rats advances by 2 positions at every game steps.
     */
    public void advance() {
        position += Advance.RAT.getValue();
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     *
     * @return the number of coins.
     */
    public int getCoins() {
        return Coin.RAT.getValue();
    }

    /**
     * Initialise Rat's health points.
     */
    public Rat() {
        health = Health.RAT.getValue();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return the name of the class, position and health points of the Enemy.
     */
    public String toString() {
        return "RAT:\t\t" + super.toString();
    }
}
