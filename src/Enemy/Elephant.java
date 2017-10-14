package Enemy;

public class Elephant extends Enemy {
    /**
     * Elephant start with a health of 10.
     * Elephant advances by 1 position at every game step
     */
    private static final int ELEPHANT_HEALTH = 10;
    private static final int ELEPHANT_POSITION_ADVANCEMENT = 1;

    public void advance() {
        position -= ELEPHANT_POSITION_ADVANCEMENT;
    }

    public Elephant(int position) {
        super(position);
        health = ELEPHANT_HEALTH;
    }

    public String toString() {
        return "Elephant:\t" + super.toString();
    }
}
