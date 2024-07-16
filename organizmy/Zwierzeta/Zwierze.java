package organizmy.Zwierzeta;
import Swiat.Swiat;
import organizmy.Organizm;

public abstract class Zwierze extends Organizm {

    public Zwierze(int str, int ini, int X, int Y, Swiat s) {
        super(str, ini, X, Y, s);
    }

    public abstract Organizm copyThis();

    public void action() {
        swiat.random.setSeed(swiat.random.nextLong());
        int dir = swiat.random.nextInt(4);
        int[] move = moveInDir(dir, 1);
        Organizm o = swiat.OrganizmOnPos(move[0], move[1]);

        if (o != null) {
            if (colision(o))
                move(move[0], move[1]);
        }
        else {
            move(move[0], move[1]);
        }
    }

    @Override
    public boolean atack(Organizm o) {
        return str >= o.getStr();
    }

    @Override
    public boolean defence(Organizm o) {
        return str > o.getStr();
    }
}
