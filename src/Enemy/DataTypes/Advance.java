package Enemy.DataTypes;

/**
 * This is a set of predefined constants of advance points for every Enemy's type; Rat, Elephant and Dragon.
 */
public enum Advance {
    RAT (2),
    ELEPHANT (1),
    DRAGON (10);

    private final int points;

    Advance(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
