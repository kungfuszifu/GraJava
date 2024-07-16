package organizmy.Zwierzeta.Gatunki;

import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Antylopa extends Zwierze {
    public Antylopa(int X, int Y, Swiat s) {
        super(4, 4, X, Y, s);
    }

    @Override
    public Organizm copyThis() {
        return new Antylopa(X, Y, swiat);
    }

    @Override
    public boolean defence(Organizm o) {
        int chance = swiat.random.nextInt(2);
        if (chance == 0) {
            chance = swiat.random.nextInt(4);
            int[] move = moveInDir(chance, 1);
            while (swiat.OrganizmOnPos(move[0], move[1]) != null) {
                swiat.random.setSeed(swiat.random.nextInt());
                chance = swiat.random.nextInt(4);
                move = moveInDir(chance, 1);
            }
            this.move(move[0], move[1]);
            return true;
        }
        else
            return super.defence(o);
    }
}
