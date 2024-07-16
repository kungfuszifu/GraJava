package organizmy.Zwierzeta.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Owca extends Zwierze {
    public Owca (int X, int Y, Swiat s) {
        super(4, 4, X, Y, s);
    }

    @Override
    public Organizm copyThis() {
        return new Owca(X, Y, swiat);
    }

}
