package Tower;

/**
 * This is a set of predefined constants of damage points for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum DamageType {
    SLINGSHOT (1),
    CATAPULT (5),
    THE_WALL(10);

    private final int points;

    DamageType(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
