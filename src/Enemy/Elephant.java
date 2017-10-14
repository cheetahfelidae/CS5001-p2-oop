package Enemy;

import Enemy.DataTypes.Advance;
import Enemy.DataTypes.Coin;
import Enemy.DataTypes.Health;

public class Elephant extends Enemy {
    public void advance() {
        position -= Advance.ELEPHANT.to_int();
    }

    public int get_coins() {
        return Coin.ELEPHANT.to_int();
    }

    public Elephant(int position) {
        super(position);
        health = Health.ELEPHANT.to_int();
    }

    public String toString() {
        return "ELEPHANT:\t" + super.toString();
    }
}
