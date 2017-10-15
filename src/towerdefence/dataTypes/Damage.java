package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of damage points for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum Damage {
    /**
     * constant damage points for Slingshot.
     */
    SLINGSHOT(1),
    /**
     * constant damage points for Catapult.
     */
    CATAPULT(5),
    /**
     * constant damage points for The Wall.
     */
    THE_WALL(10);

    private final int points;

    Damage(int points) {
        this.points = points;
    }

    /**
     * Get value of the damage points.
     *
     * @return a value of the damage points.
     */
    public int getValue() {
        return points;
    }
}
