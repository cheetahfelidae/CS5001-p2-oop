package Enemy;

import Tower.Tower;

public class Enemy {
    protected int health;
    protected int position;

    /**
     * An enemy dies when their health level <= 0
     *
     * @return the current health level of an enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * @return the current position of an enemy
     */
    public int getPosition() {
        return position;
    }


    /**
     * It is called at every game step by the advance() of the Game Class.
     * This method is responsible for updating the position of the enemy
     */
    public void advance() {}

    /**
     * This method is called when a tower hits an enemy.
     * The health of the enemy will be updated accordingly.
     *
     * @param t The hitting tower
     */
    public void hit(Tower t) {
        health -= t.get_damage();
    }

    /**
     *
     * @return
     */
    public String toString() {
        return "Position = " + position + "\t\tHealth = " + health;
    }

    /**
     *
     * @param position
     */
    public Enemy(int position) {
        this.position = position;
    }
}
