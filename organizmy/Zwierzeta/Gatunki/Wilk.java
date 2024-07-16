package organizmy.Zwierzeta.Gatunki;
import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Wilk extends Zwierze {

    public Wilk( int X, int Y, Swiat s) {
        super(9, 5, X, Y, s);
    }

    @Override
    public Organizm copyThis() {
        return new Wilk(X, Y, swiat);
    }
}
