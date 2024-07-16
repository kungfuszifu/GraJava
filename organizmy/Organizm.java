package organizmy;

import Swiat.Swiat;

public abstract class Organizm {
    protected int str, ini, X, Y;
    protected boolean dead, canHaveChildren;
    protected Swiat swiat;

    public Organizm(int str, int ini, int X, int Y, Swiat s) {
        this.str = str;
        this.ini = ini;
        this.X = X;
        this.Y = Y;
        this.swiat = s;
        this.dead = false;
        this.canHaveChildren = true;
    }

    public abstract Organizm copyThis();

    protected int[] moveInDir(int dir, int range) {
        int[] pos = {X, Y};
        if (dir == 0) {
            pos[0] += range;
        }
        else if (dir == 1) {
            pos[0] -= range;
        }
        else if (dir == 2) {
            pos[1] += range;
        }
        else {
            pos[1] -= range;
        }

        swiat.random.setSeed(swiat.random.nextInt());
        if (pos[0] >= swiat.MAP_SIZE || pos[0] < 0) {
            dir = swiat.random.nextInt(4);
            return moveInDir(dir, range);
        }
        if (pos[1] >= swiat.MAP_SIZE || pos[1] < 0) {
            dir = swiat.random.nextInt(4);
            return moveInDir(dir, range);
        }

        return pos;
    }

    public void move(int x, int y) {
        X = x;
        Y = y;
        if (X >= swiat.MAP_SIZE)
            X = swiat.MAP_SIZE-1;
        else if (X <= 0)
            X = 0;
        if (Y >= swiat.MAP_SIZE)
            Y = swiat.MAP_SIZE-1;
        else if (Y <= 0)
            Y = 0;
    }

    public abstract void action();
    public abstract boolean atack(Organizm o);
    public abstract boolean defence(Organizm o);

    public boolean colision(Organizm o) {
        if (this.getClass() == o.getClass()) {
            if (!this.canHaveChildren)
                return false;

            this.haveChildren();
            this.canHaveChildren = false;
            swiat.addPomijane(o);
            System.out.println(this.getClass().getSimpleName()+" romznaza sie");
            return false;
        }
        else {
            boolean atack = this.atack(o), defence = o.defence(this);
            if (atack && !defence) {
                o.dead = true;
                System.out.println(this.getClass().getSimpleName()+" zabija "+o.getClass().getSimpleName());
                return true;
            }
            else if (!atack && defence) {
                this.dead = true;
                System.out.println(o.getClass().getSimpleName()+" broni sie przed "+this.getClass().getSimpleName());
                return true;
            }
            else { // inne wiadomosci bezposrednio w klasach
                return false;
            }
        }
    }

    private int[] findUnoccupiedSpace() {
        int counter=0, range=1;
        int x=swiat.random.nextInt(-range,range+1);
        int y=swiat.random.nextInt(-range,range+1);

        while (swiat.OrganizmOnPos(x, y) != null) {
            swiat.random.setSeed(swiat.random.nextInt());
            if (counter == 10) {
                range++;
                counter=0;
            }
            x=swiat.random.nextInt(-range,range+1);
            y=swiat.random.nextInt(-range,range+1);
            counter++;
        }
        return new int[]{X+x, Y+y};
    }

    public void haveChildren() {
        Organizm child = this.copyThis();
        int[] move = findUnoccupiedSpace();
        child.move(move[0], move[1]);
        swiat.addOrganism(child);
        this.canHaveChildren = false;
    }

    public int getIni() {
        return ini;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean isDead() {
        return dead;
    }

    public void die() {
        dead = true;
    }
}
