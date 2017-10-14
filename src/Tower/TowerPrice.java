package Tower;

/**
 * This is a set of predefined constants of costs for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum TowerPrice {
    SLINGSHOT (1),
    CATAPULT (5),
    THE_WALL(10);

    private final int points;

    TowerPrice(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
