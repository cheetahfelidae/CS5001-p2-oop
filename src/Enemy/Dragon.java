package Enemy;

public class Dragon extends Enemy{
    public void advance() {
        position -= AdvanceType.DRAGON.to_int();
    }

    public Dragon(int position) {
        super(position);
        health = HealthType.DRAGON.to_int();
    }

    public String toString() {
        return "DRAGON:\t" + super.toString();
    }
}
