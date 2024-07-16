package organizmy.Zwierzeta.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Lis extends Zwierze {

    public Lis(int X, int Y, Swiat s) {
        super(3, 7, X, Y, s);
    }

    @Override
    public Organizm copyThis() {
        return new Lis(X, Y, swiat);
    }

    @Override
    public void action() {
        int dir = swiat.random.nextInt(3);
        int[] move = moveInDir(dir, 1);
        Organizm o = swiat.OrganizmOnPos(move[0], move[1]);

        if (o != null && o.getStr() < 5) {
            if (colision(o))
                move(move[0], move[1]);
        }
        else {
            move(move[0], move[1]);
        }
    }
}
