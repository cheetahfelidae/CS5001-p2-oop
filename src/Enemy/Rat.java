package Enemy;

public class Rat extends Enemy {
    public void advance() {
        position -= AdvanceType.RAT.to_int();
    }

    public Rat(int position) {
        super(position);
        health = HealthType.RAT.to_int();
    }

    public String toString() {
        return "RAT:\t\t" + super.toString();
    }
}
