package towerdefence;

/**
 * This is a super class for Rat, Elephant and Dragon classes.
 */
public class Enemy {
    protected int health = 0;
    protected int position = 0;
    protected boolean isShot = false;

    /**
     * This method used by advance() of Game class to check if an enemy has been shot for a particular game step before, so it will not be shot repeatedly in the same game step.
     *
     * @return indicate if an enemy has already shot.
     */
    public boolean isShot() {
        return isShot;
    }

    /**
     * This method is used to mark that an enemy is shot.
     *
     */
    public void resetIsShot() {
        this.isShot = false;
    }

    /**
     * An enemy dies when their health level <= 0.
     *
     * @return the current health level of an enemy.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get the current position of an enemy.
     *
     * @return the current position of an enemy.
     */
    public int getPosition() {
        return position;
    }

    /**
     * It is called at every game step by the advance() of the Game Class.
     * This method is responsible for updating the position of the enemy.
     */
    public void advance() {
    }

    /**
     * This method is called when a tower hits an enemy.
     * The health of the enemy will be updated accordingly.
     *
     * @param t The hitting tower
     */
    public void hit(Tower t) {
        if (getHealth() > 0 && getPosition() <= t.getPosition()) {
            health -= t.getDamage();
        }
    }

    /**
     * This method is called when an enemy is killed to reward the player some coins.
     *
     * @return the number of coins.
     */
    public int getCoins() {
        return 0;
    }

    /**
     * This method is used to inspect the state of the game during testing and debugging.
     *
     * @return position and health points of the Enemy.
     */
    public String toString() {
        return "Position = " + position + "\t\tHealth = " + health;
    }

}
