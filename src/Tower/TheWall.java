package Tower;

public class TheWall extends Tower {
    public TheWall(int position) {
        super(position);
        damage = DamageType.THE_WALL.to_int();
    }
}
