package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of the health points for every Tower's type; Rat, Elephant and Dragon.
 */
public enum Health {
    /**
     * constant value of the health points for Rat.
     */
    RAT(1),
    /**
     * constant value of the health points for Elephant.
     */
    ELEPHANT(10),
    /**
     * constant value of the health points for Dragon.
     */
    DRAGON(5);

    private final int points;

    Health(int points) {
        this.points = points;
    }

    /**
     * Get value of the the required coins.
     *
     * @return a value of the the required coins.
     */
    public int value() {
        return points;
    }
}
