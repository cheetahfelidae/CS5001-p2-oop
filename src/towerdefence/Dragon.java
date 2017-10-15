package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

public class Dragon extends Enemy{
    /**
     * Extended: Dragon advances by 10 positions at every game steps
     */
    public void advance() {
        position += Advance.DRAGON.to_int();
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     * @return the number of coins.
     */
    public int getCoins() {
        return Coin.DRAGON.to_int();
    }

    /**
     * Initialise Dragon's health points.
     */
    public Dragon() {
        health = Health.DRAGON.to_int();
    }


    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return the name of the class, position and health points of the Enemy.
     */
    public String toString() {
        return "DRAGON: \t" + super.toString();
    }
}
