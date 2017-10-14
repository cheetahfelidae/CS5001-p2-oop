package Tower;

public class Tower {
    protected int damage;
    protected int position;
    /**
     * @return how much damage a given tower makes when they hit an enemy.
     */
    public int get_damage() {
        return damage;
    }

    /**
     * Towers may be created at any position, but they will hit enemies whose position is <= to theirs.
     * @return position of a tower.
     */
    public int get_position() {
        return position;
    }

    /**
     * @param timeStep the current time step of the game.
     * @return
     */
    public boolean will_fire(int timeStep) {
        return true;
    }


    public String toString() {
        return "Position: " + position;
    }

    public Tower(int position) {
        this.position = position;
    }
}
