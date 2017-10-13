public class Enemy {
    /**
     * An enemy dies when their heal level <= 0
     * @return the current health level of an enemy.
     */
    public int getHealth() {
       return 0;
    }

    /**
     *
     * @return the current position of an enemy
     */
    public int getPosition() {
        return 0;
    }


    /**
     * It is called at every game step by the advance() of the Game Class.
     * This method is responsible for updating the position of the enemy
     */
    public void advance() {
        
    }

    /**
     * This method is called when a tower hits an enemy. The tower is passed in as a parameter in this case.
     * The health of the enemy will be updated accordingly.
     * @param t
     */
    public void hit(Tower t) {

    }

}
