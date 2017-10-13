public class Tower {
    /**
     * @return how much damage a given tower makes when they hit an enemy
     */
    public int getDamange() {
        return 0;
    }

    /**
     * Towers may be created at any position, but they will hit enemies whose position is less than or equal to theirs.
     * @return position of a tower.
     */
    public int getPosition() {
        return 0;
    }

    public boolean willFire(int timeStep) {
        return false;
    }
}
