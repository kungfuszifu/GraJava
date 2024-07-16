package organizmy.Rosliny.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Rosliny.Roslina;

public class Guarana extends Roslina {
    public Guarana(int X, int Y, Swiat s) {
        super(0, 0, X, Y, s);
    }

    @Override
    public Roslina copyThis() {
        return new Guarana(X, Y, swiat);
    }

    @Override
    public boolean defence(Organizm o) {
        boolean def = super.defence(o);
        if (!def) {
            o.setStr(o.getStr() + 3);
            System.out.println("Sila "+o.getClass().getSimpleName()+" wzrasta o 3");
        }
        return def;
    }
}
