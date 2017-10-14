package Tower;

public class TheWall extends Tower {
    public TheWall(int position) {
        super(position);
        damage = DamageType.THE_WALL.to_int();
    }

    public String toString() {
        return "TheWall:\n\n" + super.toString();
    }
}
