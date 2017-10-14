package Enemy;

/**
 *  This is a set of predefined constants of health points for every Enemy's type; Rat, Elephant and Dragon.
 */
public enum HealthType {
    RAT (1),
    ELEPHANT (10),
    DRAGON (5);

    private final int points;

    HealthType(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
