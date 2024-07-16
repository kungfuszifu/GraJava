package Swiat;
import java.util.Random;
import java.util.Vector;
import Exceptions.CzlowiekIsDead;
import organizmy.Organizm;
import organizmy.OrganizmComparator;
import organizmy.Rosliny.Gatunki.*;
import organizmy.Zwierzeta.Gatunki.*;

public class Swiat {
    public final int MAP_SIZE = 25;
    public Random random;
    Vector<Organizm> organizms;
    Vector<Organizm> children;
    Vector<Organizm> pomijane;


    public class AccessPrivate {
        private AccessPrivate() { }
        public Vector<Organizm> accessVector() {
            return organizms; }
    }

    public void giveAccess(InstanceManager i) {
        i.getAccess(new AccessPrivate());
    }

    public Swiat() {
        random = new Random();
        organizms = new Vector<>();
        children = new Vector<>();
        pomijane = new Vector<>();

        int x = random.nextInt(MAP_SIZE), y = random.nextInt(MAP_SIZE);
        addOrganism(new Czlowiek(x, y, this));

        for (int i=0; i<6; i++) {
            while (OrganizmOnPos(x, y) != null) {
                random.setSeed(random.nextLong());
                x = random.nextInt(MAP_SIZE);
                y = random.nextInt(MAP_SIZE);
            }
            String name = classNameFromKey(i%10);
            addOrganism(generatorFromString(name, x, y));
        }
        moveChildren();
    }

    public void moveWolrd(int czlowiekDir) {

        catchCzlowiekDead();
        for (Organizm o: organizms) {

            if (isPomijany(o) || o.isDead())
                continue;

            if (o instanceof Czlowiek)
                ((Czlowiek) o).action(czlowiekDir);
            else
                o.action();
        }
        catchCzlowiekDead();
        moveChildren();
        pomijane.clear();
        organizms.sort(new OrganizmComparator());
    }

    public String classNameFromKey(int key) {
        if (key == 0)
            return "Wilk";
        else if (key == 1)
            return "Lis";
        else if (key == 2)
            return "Owca";
        else if (key == 3)
            return "Zolw";
        else if (key == 4)
            return "Antylopa";
        else if (key == 5)
            return "Trawa";
        else if (key == 6)
            return "Mlecz";
        else if (key == 7)
            return "Guarana";
        else if (key == 8)
            return "Jagody";
        else if (key == 9)
            return "Barszcz";
        return "";
    }

    public Organizm generatorFromString(String name, int x, int y) {
        Organizm o = switch (name) {
            case "Czlowiek" -> new Czlowiek(x, y, this);
            case "Wilk" -> new Wilk(x, y, this);
            case "Lis" -> new Lis(x, y, this);
            case "Owca" -> new Owca(x, y, this);
            case "Zolw" -> new Zolw(x, y, this);
            case "Antylopa" -> new Antylopa(x, y, this);
            case "Trawa" -> new Trawa(x, y, this);
            case "Mlecz" -> new Mlecz(x, y, this);
            case "Guarana" -> new Guarana(x, y, this);
            case "Jagody" -> new Jagody(x, y, this);
            case "Barszcz" -> new Barszcz(x, y, this);
            default -> null;
        };
        return o;
    }

    public Organizm OrganizmOnPos(int x, int y) {
        for (Organizm o: organizms) {
            if (o.getX() == x && o.getY() == y && !o.isDead()) {
                return o;
            }
        }
        for (Organizm o: children) {
            if (o.getX() == x && o.getY() == y && !o.isDead()) {
                return o;
            }
        }
        return null;
    }

    private void catchCzlowiekDead() {
        try {
            getCzlowiek();
        }
        catch (CzlowiekIsDead ex) {
            System.out.println("Czlowiek nie zyje");
            System.exit(1);
        }
    }

    public Organizm getCzlowiek() throws CzlowiekIsDead {
        for (Organizm o: organizms) {
            if (o instanceof Czlowiek) {
                if (!o.isDead())
                    return o;
                else
                    throw new CzlowiekIsDead("Czlowiek nie zyje");
            }
        }
        return null;
    }

    public void addPomijane(Organizm o) {
        pomijane.add(o);
    }

    private boolean isPomijany(Organizm o) {
        for (Organizm p: pomijane) {
            if (p == o)
                return true;
        }
        return false;
    }

    public void addOrganism(Organizm o) {
        children.add(o);
    }

    public void moveChildren() {
        organizms.addAll(children);
        children.clear();
    }

    public void clear() {
        organizms.clear();
        pomijane.clear();
        children.clear();
    }
}
