package Tower;

import Tower.DataTypes.Damage;
import Tower.DataTypes.WaitingStep;

public class Catapult extends Tower {
    public Catapult(int position) {
        super(position);
        damage = Damage.CATAPULT.to_int();
    }

    public boolean will_fire(int timeStep) {
        return timeStep % WaitingStep.CATAPULT.to_int() != 0;
    }

    public String toString() {
        return "Catapult:\n\n" + super.toString();
    }
}
   