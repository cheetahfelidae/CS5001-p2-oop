package towerdefence;

import towerdefence.dataTypes.Damage;

/**
 * Slingshot is a small tower and can damage an enemy only 1 health point but is very quick to load again.
 */
public class Slingshot extends Tower {
    /**
     * set the position and damage points for the tower.
     *
     * @param position the position which will be placed at.
     */
    public Slingshot(int position) {
        super(position);
        damage = Damage.SLINGSHOT.getValue();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return the name of the class, position and damage points of the tower.
     */
    public String toString() {
        return "Slingshot:\n\n" + super.toString();
    }
}
