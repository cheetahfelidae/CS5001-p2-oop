package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;

public class Rat extends Enemy {
    public void advance() {
        position -= Advance.RAT.to_int();
    }

    public int get_coins() {
        return Coin.RAT.to_int();
    }

    public Rat() {
        super();
        health = Health.RAT.to_int();
    }

    public String toString() {
        return "RAT:\t\t" + super.toString();
    }
}
