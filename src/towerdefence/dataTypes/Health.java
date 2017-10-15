package towerdefence.dataTypes;

/**
 *  This is a set of predefined constants of health points for every Tower's type; Rat, Elephant and Dragon.
 */
public enum Health {
    RAT (1),
    ELEPHANT (10),
    DRAGON (5);

    private final int points;

    Health(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
