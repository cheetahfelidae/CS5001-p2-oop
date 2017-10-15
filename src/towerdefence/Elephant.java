package towerdefence;

import towerdefence.dataTypes.Advance;
import towerdefence.dataTypes.Coin;
import towerdefence.dataTypes.Health;
import towerdefence.dataTypes.WaitingStep;

public class Elephant extends Enemy {
    public void advance() {
        if (steps % WaitingStep.ELEPHANT.to_int() == 0) {
            position += Advance.ELEPHANT.to_int();
        }
        steps++;
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
