package organizmy.Zwierzeta.Gatunki;


import java.awt.event.KeyEvent;
import Swiat.Swiat;
import organizmy.Organizm;
import organizmy.Zwierzeta.Zwierze;

public class Czlowiek extends Zwierze {

    private int powerCd;

    public Czlowiek(int X, int Y, Swiat s) {
        super(5, 4, X, Y, s);
        powerCd = 0;
    }

    @Override
    public Organizm copyThis() {
        return new Czlowiek(X, Y, swiat);
    }

    public void action(int dir) {
        int x=0, y=0, range=1;
        if (powerCd>2)
            range = 2;
        else if (powerCd>0) {
            int chance = swiat.random.nextInt(2);
            if (chance == 0)
                range = 2;

        }
       if (powerCd>0)
           System.out.println("Pozostalo "+--powerCd+" uzyc");

        if (dir == KeyEvent.VK_UP) {
            y-=range;
        }
        else if (dir == KeyEvent.VK_DOWN) {
            y+=range;
        }
        else if (dir == KeyEvent.VK_LEFT) {
            x-=range;
        }
        else if (dir == KeyEvent.VK_RIGHT) {
            x+=range;
        }
        x += X;
        y += Y;
        Organizm o = swiat.OrganizmOnPos(x, y);

        if (o != null) {
            if (colision(o))
                move(x, y);
        }
        else {
            move(x, y);
        }
    }

    public void activatePower() {
        if (powerCd<=0) {
            System.out.println("Umiejetnosc czlowieka aktywna");
            powerCd = 5;
        }
        else
            System.out.println("Umiejetnosc w uzyciu");

    }


}
