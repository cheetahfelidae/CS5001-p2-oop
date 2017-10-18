package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of costs for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum Price {
    /**
     * constant value of the required coins for Slingshot.
     */
    SLINGSHOT(5),
    /**
     * constant value of the required coins for Catapult.
     */
    CATAPULT(10),
    /**
     * constant value of the required coins for The Wall.
     */
    THE_WALL(25);

    private final int points;

    Price(int points) {
        this.points = points;
    }

    /**
     * Get value of the the required coins.
     *
     * @return a value of the the required coins.
     */
    public int value() {
        return points;
    }
}
