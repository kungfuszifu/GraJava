package organizmy.Rosliny.Gatunki;

import Swiat.Swiat;
import organizmy.Rosliny.Roslina;

public class Trawa extends Roslina {
    public Trawa(int X, int Y, Swiat s) {
        super(0 , 0, X, Y, s);
    }

    @Override
    public Roslina copyThis() {
        return new Trawa(X, Y, swiat);
    }
}
