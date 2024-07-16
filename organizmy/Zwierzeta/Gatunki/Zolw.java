package organizmy.Zwierzeta.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Zolw extends Zwierze {

    public Zolw(int X, int Y, Swiat s) {
        super(2, 1, X, Y, s);
    }

    @Override
    public Organizm copyThis() {
        return new Zolw(X, Y, swiat);
    }

    @Override
    public void action() {
        int chance = swiat.random.nextInt(3);
        if (chance == 0)
            super.action();
    }

    @Override
    public boolean defence(Organizm o) {
        if (o.getStr() < 5)
            return true;
        boolean def = super.defence(o);
        if (def)
            System.out.println("Zolw odpiera atak "+o.getClass().getSimpleName());
        return def;
    }
}
