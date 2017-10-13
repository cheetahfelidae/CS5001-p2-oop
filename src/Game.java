import java.util.ArrayList;

public class Game {
    private ArrayList<Enemy> enemies;
    private ArrayList<Tower> towers;

    /**
     *
     * @param corridorLength
     */
    public Game(int corridorLength) {

    }

    /**
     * 1. Shooting from the towers. Towers can't hit enemies that have passed them,
     *      so they will only hit enemy whose position is <= o the position of the tower.
     * 2. Towers shoot as soon as they are loaded. This indicated by the willFire() returning true.
     *      Catapults need to wait a number of time steps before they can shoot again.
     *      The parameter to the willFire() is the current time step of the game.
     */
    public void advance() {

    }

    public static void main(String[] args) {

    }
}
