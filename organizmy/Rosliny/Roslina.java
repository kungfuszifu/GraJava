package organizmy.Rosliny;

import Swiat.Swiat;
import organizmy.Organizm;

public abstract class Roslina extends Organizm {
    public Roslina(int str, int ini, int X, int Y, Swiat s) {
        super(str, ini, X, Y, s);
    }

    public abstract Roslina copyThis();

    public void action() {
        int chance = swiat.random.nextInt(50);
        if (chance == 0) {
            haveChildren();
            canHaveChildren = false;
            System.out.println(getClass().getSimpleName()+" rozpyla sie");
        }
    }

    @Override
    public boolean atack(Organizm o) {
        return this.str >= o.getStr();
    }

    @Override
    public boolean defence(Organizm o) {
        return this.str > o.getStr();
    }
}
