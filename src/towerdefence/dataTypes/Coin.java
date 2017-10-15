package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of reward coins for every Tower's type; Rat, Elephant and Dragon.
 */
public enum Coin {
    RAT (2),
    ELEPHANT (5),
    DRAGON (8);

    private final int points;

    Coin(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}