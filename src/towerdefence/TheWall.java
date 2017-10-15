package towerdefence;


import towerdefence.dataTypes.Damage;

public class TheWall extends Tower {
    /**
     * Set the position and damage points for the tower.
     * @param position the position of the tower which the player want to place.
     */
    public TheWall(int position) {
        super(position);
        damage = Damage.THE_WALL.to_int();
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return the name of the Tower, position and damage points of the tower.
     */
    public String toString() {
        return "TheWall:\n\n" + super.toString();
    }
}
