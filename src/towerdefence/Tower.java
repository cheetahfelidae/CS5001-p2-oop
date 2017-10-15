package towerdefence;

public class Tower {
    protected int damage = 0;
    protected int position;
    /**
     * Get how much damage a given tower makes when they hit an enemy.
     * @return how much damage the tower makes.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Towers may be created at any position, but they will hit enemies whose position is <= to theirs.
     * @return the position of a tower.
     */
    public int getPosition() {
        return position;
    }

    /**
     * This method is used to check if the tower is loaded.
     * @param gameSteps the current time step of the game.
     * @return the state of the weapon.
     */
    public boolean willFire(int gameSteps) {
        return true;
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     * @return position and damage points of the Tower.
     */
    public String toString() {
        return "Position = " + position + "\n\nDamage Points = " + damage;
    }

    /**
     * Set the position of the tower.
     * @param position the position of the Tower which the player want to place.
     */
    public Tower(int position) {
        this.position = position;
    }
}
