package towerdefence;

import towerdefence.dataTypes.Damage;
import towerdefence.dataTypes.WaitingStep;

public class Catapult extends Tower {
    /**
     * Set the position and damage points for the tower.
     * @param position the position of the tower which the player want to place.
     */
    public Catapult(int position) {
        super(position);
        damage = Damage.CATAPULT.to_int();
    }

    /**
     * This method is used to check if the tower is loaded.
     * The Catapult need to load every 3 game steps before it can be used.
     * @param gameSteps the current time step of the game.
     * @return the state of the weapon.
     */
    public boolean willFire(int gameSteps) {
        return gameSteps % WaitingStep.CATAPULT.to_int() == 0;
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return position and damage points of the Tower.
     */
    public String toString() {
        return "Catapult:\n\n" + super.toString();
    }
}
   