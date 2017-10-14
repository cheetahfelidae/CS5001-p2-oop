package Tower;

public class Catapult extends Tower {
    public Catapult(int position) {
        super(position);
        damage = DamageType.CATAPULT.to_int();
    }

    /**
     * Catapults need to wait a number of time steps before they can shoot again.
     *
     * @param timeStep the current time step of the game.
     * @return
     */
    public boolean will_fire(int timeStep) {
        return timeStep % 3 != 0;
    }

    public String toString() {
        return "Catapult:\n\n" + super.toString();
    }
}
   