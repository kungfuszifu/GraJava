package organizmy.Rosliny.Gatunki;

import Swiat.Swiat;
import organizmy.Rosliny.Roslina;

public class Mlecz extends Roslina {
    public Mlecz(int X, int Y, Swiat s) {
        super(0, 0, X, Y, s);
    }

    @Override
    public Roslina copyThis() {
        return new Mlecz(X, Y, swiat);
    }

    @Override
    public void action() {
        for (int i=0; i<3; i++) {
            int chance = swiat.random.nextInt(50);
            if (chance == 0) {
                haveChildren();
                break;
            }
        }
    }
}
