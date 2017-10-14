package Tower;

/**
 * This is a set of predefined constants of damage points of every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum DamageType {

    SLINGSHOT (1),
    CATAPULT (5),
    THE_WALL(10);

    private final int damage_points;

    DamageType(int damage_points) {
        this.damage_points = damage_points;
    }

    public int to_int() {
        return damage_points;
    }
}
