package towerdefence;

import towerdefence.dataTypes.Damage;

public class Slingshot extends Tower {
    /**
     * set the position and damage points for the tower.
     * @param position the position of the tower which the player want to place.
     */
    public Slingshot(int position) {
        super(position);
        damage = Damage.SLINGSHOT.to_int();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return the name of the class, position and damage points of the tower.
     */
    public String toString() {
        return "Slingshot:\n\n" + super.toString();
    }
}
