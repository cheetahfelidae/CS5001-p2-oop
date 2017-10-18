package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of the reward coins for every Tower's type; Rat, Elephant and Dragon.
 */
public enum Coin {
    /**
     * constant reward coins for Rat.
     */
    RAT(2),
    /**
     * constant reward coins for Elephant.
     */
    ELEPHANT(5),
    /**
     * constant reward coins for Dragon.
     */
    DRAGON(12);

    private final int points;

    Coin(int points) {
        this.points = points;
    }

    /**
     * Get value of the the reward coins.
     *
     * @return a value of the the reward coins.
     */
    public int value() {
        return points;
    }
}
