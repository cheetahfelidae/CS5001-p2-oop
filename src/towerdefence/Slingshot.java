package towerdefence;


import towerdefence.dataTypes.Damage;

public class Slingshot extends Tower {
    public Slingshot(int position) {
        super(position);
        damage = Damage.SLINGSHOT.to_int();
    }

    public String toString() {
        return "Slingshot:\n\n" + super.toString();
    }
}
