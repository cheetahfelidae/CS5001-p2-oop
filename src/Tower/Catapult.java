package Tower;

public class Catapult extends Tower {
    private static final int CATAPULT_DAMAGE = 5;
    /**
     * Catapult can damage an enemy by 5 health points
     */
    public Catapult(int position) {
        super(position);
        damage = CATAPULT_DAMAGE;
    }

    /**
     * Catapults need to wait a number of time steps before they can shoot again.
     * @param timeStep the current time step of the game.
     * @return
     */
    public boolean will_fire(int timeStep) {
        return timeStep % 3 != 0;
    }
}
   