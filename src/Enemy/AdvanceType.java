package Enemy;

/**
 * This is a set of predefined constants of advance points of every enemy's type; Rat, Elephant and Dragon.
 */
public enum AdvanceType {
    RAT (2),
    ELEPHANT (1),
    DRAGON (10);

    private final int points;

    AdvanceType(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
