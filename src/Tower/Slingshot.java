package Tower;

public class Slingshot extends Tower {
    private static final int SLINGSHOT_DAMAGE = 1;
    /**
     * Slingshot can damage an enemy by 1 health point
     */
    public Slingshot(int position) {
        super(position);
        damage = SLINGSHOT_DAMAGE;
    }
}
