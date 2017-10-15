package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

public class Dragon extends Enemy{
    public void advance() {
        position -= Advance.DRAGON.to_int();
    }

    public int get_coins() {
        return Coin.DRAGON.to_int();
    }

    public Dragon() {
        super();
        health = Health.DRAGON.to_int();
    }

    public String toString() {
        return "DRAGON: \t" + super.toString();
    }
}
