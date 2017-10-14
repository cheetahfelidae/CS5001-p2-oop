package Enemy;

public class Rat extends Enemy {
    /**
     * Rat start with a health of 1.
     * Rat advances by 2 positions at every game step.
     */
    private static final int RAT_HEALTH = 1;
    private static final int RAT_POSITION_ADVANCEMENT = 2;

    public void advance() {
        position -= RAT_POSITION_ADVANCEMENT;
    }

    public Rat(int position) {
        super(position);
        health = RAT_HEALTH;
    }

    public String toString() {
        return "Rat:\t\t" + super.toString();
    }
}
