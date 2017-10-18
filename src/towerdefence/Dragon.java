package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

/**
 * Dragon is an additional enemy with medium size and can travel faster than other types of enemies.
 */
public class Dragon extends Enemy {
    /**
     * Extended: Dragon advances by 10 positions at every game steps.
     */
    public void advance() {
        position += Advance.DRAGON.value();
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     *
     * @return the number of coins.
     */
    public int getCoins() {
        return Coin.DRAGON.value();
    }

    /**
     * Initialise Dragon's health points.
     */
    public Dragon() {
        health = Health.DRAGON.value();
    }


    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return the name of the class, position and health points of the Enemy.
     */
    public String toString() {
        return "DRAGON: \t" + super.toString();
    }
}
