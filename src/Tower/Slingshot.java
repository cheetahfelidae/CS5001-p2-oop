package Tower;

public class Slingshot extends Tower {
    public Slingshot(int position) {
        super(position);
        damage = DamageType.SLINGSHOT.to_int();
    }

    public String toString() {
        return "Slingshot:\n\n" + super.toString();
    }
}
