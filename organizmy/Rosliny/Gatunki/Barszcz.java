package organizmy.Rosliny.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Rosliny.Roslina;
import organizmy.Zwierzeta.Zwierze;

public class Barszcz extends Roslina {
    public Barszcz(int X, int Y, Swiat s) {
        super(10, 0, X, Y, s);
    }

    @Override
    public Roslina copyThis() {
        return new Barszcz(X, Y, swiat);
    }

    @Override
    public void action() {
        int xl=X, xr=X, yd=Y, yu=Y;
        if (X > 0)
            xl--;
        if (X < swiat.MAP_SIZE-1)
            xr++;
        if (Y > 0)
            yd--;
        if (Y < swiat.MAP_SIZE-1)
            yu++;
        for (int y=yd; y<=yu; y++) {
            for (int x=xl; x<=xr; x++) {
                if (x == X && y == Y)
                    continue;

                Organizm o = swiat.OrganizmOnPos(x, y);
                if (o instanceof Zwierze) {
                    o.die();
                    System.out.println(o.getClass().getSimpleName()+" zostaje zatruty przez barszcz");
                }
            }
        }
        super.action();
    }
}
