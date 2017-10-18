package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of the advance steps for every Tower's type; Rat, Elephant and Dragon.
 */
public enum Advance {
    /**
     * constant advance steps for Rat.
     */
    RAT(2),
    /**
     * constant advance steps for Elephant.
     */
    ELEPHANT(1),
    /**
     * constant advance steps for Dragon.
     */
    DRAGON(8);

    private final int points;

    Advance(int points) {
        this.points = points;
    }

    /**
     * Get value of the the advance steps.
     *
     * @return a value of the the advance steps.
     */
    public int value() {
        return points;
    }
}
