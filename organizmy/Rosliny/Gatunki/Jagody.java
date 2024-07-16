package organizmy.Rosliny.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Rosliny.Roslina;

public class Jagody extends Roslina {
    public Jagody(int X, int Y, Swiat s) {
        super(99, 0, X, Y, s);
    }

    @Override
    public Roslina copyThis() {
        return new Jagody(X, Y, swiat);
    }

    @Override
    public boolean defence(Organizm o) {
        boolean def = super.defence(o);
        if (def)
            dead = true;
        return def;
    }
}
