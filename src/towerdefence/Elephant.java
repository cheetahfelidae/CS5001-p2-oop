package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

public class Elephant extends Enemy {
    public void advance() {
        position -= Advance.ELEPHANT.to_int();
    }

    public int get_coins() {
        return Coin.ELEPHANT.to_int();
    }

    public Elephant() {
        super();
        health = Health.ELEPHANT.to_int();
    }

    public String toString() {
        return "ELEPHANT:\t" + super.toString();
    }
}
