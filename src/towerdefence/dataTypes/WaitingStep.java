package towerdefence.dataTypes;

/**
 * This is a set of predefined constants of waiting game steps for every Tower's type; Slingshot, Catapult and The Wall.
 */
public enum WaitingStep {
    SLINGSHOT (0),
    CATAPULT (3),
    THE_WALL(0);

    private final int points;

    WaitingStep(int points) {
        this.points = points;
    }

    public int to_int() {
        return points;
    }
}
