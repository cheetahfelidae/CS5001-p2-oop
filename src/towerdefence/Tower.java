package towerdefence;

public class Tower {
    protected int damage;
    protected int position;
    /**
     * @return how much damage a given tower makes when they hit an enemy.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Towers may be created at any position, but they will hit enemies whose position is <= to theirs.
     * @return position of a tower.
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param timeStep the current time step of the game.
     * @return
     */
    public boolean willFire(int timeStep) {
        return true;
    }

    public String toString() {
        return "Position = " + position + "\n\nDamage Points = " + damage;
    }

    /**
     *
     * @param position
     */
    public Tower(int position) {
        this.position = position;
        damage = 0;
    }
}
