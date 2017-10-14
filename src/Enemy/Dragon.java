package Enemy;

import Enemy.DataTypes.Advance;
import Enemy.DataTypes.Coin;
import Enemy.DataTypes.Health;

public class Dragon extends Enemy{
    public void advance() {
        position -= Advance.DRAGON.to_int();
    }

    public int get_coins() {
        return Coin.DRAGON.to_int();
    }

    public Dragon(int position) {
        super(position);
        health = Health.DRAGON.to_int();
    }

    public String toString() {
        return "DRAGON: \t" + super.toString();
    }
}
