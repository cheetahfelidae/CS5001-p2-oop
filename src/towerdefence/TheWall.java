package towerdefence;


import towerdefence.dataTypes.Damage;

public class TheWall extends Tower {
    public TheWall(int position) {
        super(position);
        damage = Damage.THE_WALL.to_int();
    }

    public String toString() {
        return "TheWall:\n\n" + super.toString();
    }
}
