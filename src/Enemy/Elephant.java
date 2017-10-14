package Enemy;

public class Elephant extends Enemy {
    public void advance() {
        position -= AdvanceType.ELEPHANT.to_int();
    }

    public Elephant(int position) {
        super(position);
        health = HealthType.ELEPHANT.to_int();
    }

    public String toString() {
        return "ELEPHANT:\t" + super.toString();
    }
}
