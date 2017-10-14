package Enemy;

import Enemy.DataTypes.Advance;
import Enemy.DataTypes.Coin;
import Enemy.DataTypes.Health;

public class Rat extends Enemy {
    public void advance() {
        position -= Advance.RAT.to_int();
    }

    public int get_coins() {
        return Coin.RAT.to_int();
    }

    public Rat(int position) {
        super(position);
        health = Health.RAT.to_int();
    }

    public String toString() {
        return "RAT:\t\t" + super.toString();
    }
}
