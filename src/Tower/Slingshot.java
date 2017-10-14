package Tower;

import Tower.DataTypes.Damage;
import Tower.DataTypes.WaitingStep;

public class Slingshot extends Tower {
    public Slingshot(int position) {
        super(position);
        damage = Damage.SLINGSHOT.to_int();
    }

    public boolean will_fire(int timeStep) {
        return timeStep % WaitingStep.SLINGSHOT.to_int() != 0;
    }

    public String toString() {
        return "Slingshot:\n\n" + super.toString();
    }
}
