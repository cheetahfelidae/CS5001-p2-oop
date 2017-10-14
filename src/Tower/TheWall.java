package Tower;

import Tower.DataTypes.Damage;
import Tower.DataTypes.WaitingStep;

public class TheWall extends Tower {
    public TheWall(int position) {
        super(position);
        damage = Damage.THE_WALL.to_int();
    }

    public boolean will_fire(int timeStep) {
        return timeStep % WaitingStep.THE_WALL.to_int() != 0;
    }

    public String toString() {
        return "TheWall:\n\n" + super.toString();
    }
}
