package towerdefence;

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
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * It is called at every game step by the advance() of the Game Class.
     * This method is responsible for updating the position of the enemy
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
        health -= t.getDamage();
    }

    /**
     * This method is called when an enemy is killed to return some coins as a reward
     * @return the number of coins
     */
    public int get_coins() {
        return 0;
    }

    /**
     * @return
     */
    public String toString() {
        return "Position = " + position + "\t\tHealth = " + health;
    }

    /**
     *
     */
    public Enemy() {
        health = 0;
        position = 0;
    }
}