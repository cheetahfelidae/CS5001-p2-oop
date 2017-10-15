package towerdefence;


import towerdefence.dataTypes.Damage;

/**
 * The Wall is the most widest and highest tower.
 * It can kill an enemy in one shot and every game step.
 */
public class TheWall extends Tower {
    /**
     * Set the position and damage points for the tower.
     *
     * @param position the position which will be placed at.
     */
    public TheWall(int position) {
        super(position);
        damage = Damage.THE_WALL.getValue();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return the name of the Tower, position and damage points of the tower.
     */
    public String toString() {
        return "TheWall:\n\n" + super.toString();
    }
}
