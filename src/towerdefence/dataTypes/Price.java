package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of costs for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum Price {
    SLINGSHOT (5),
    CATAPULT (10),
    THE_WALL(20);

    private final int points;

    Price(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
