package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of waiting game steps for every Tower's type; Slingshot, Catapult and The Wall and for an Elephant of Enemy Class.
 */
public enum WaitingStep {
    /**
     * constant value of the waiting steps for Slingshot.
     */
    SLINGSHOT(0),
    /**
     * constant value of the waiting steps for Catapult.
     */
    CATAPULT(3),
    /**
     * constant value of the waiting steps for The Wall.
     */
    THE_WALL(0),
    /**
     * constant value of the waiting steps for Elephant.
     */
    ELEPHANT(2);

    private final int points;

    WaitingStep(int points) {
        this.points = points;
    }

    /**
     * Get value of the waiting steps.
     *
     * @return a value of the waiting steps.
     */
    public int getValue() {
        return points;
    }
}
