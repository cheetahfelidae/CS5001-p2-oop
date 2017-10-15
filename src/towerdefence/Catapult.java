package towerdefence;

import towerdefence.dataTypes.Damage;
import towerdefence.dataTypes.WaitingStep;

public class Catapult extends Tower {
    public Catapult(int position) {
        super(position);
        damage = Damage.CATAPULT.to_int();
    }

    public boolean willFire(int timeStep) {
        return timeStep % WaitingStep.CATAPULT.to_int() == 0;
    }

    public String toString() {
        return "Catapult:\n\n" + super.toString();
    }
}
   