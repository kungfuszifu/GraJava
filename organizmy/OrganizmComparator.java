package organizmy;

import java.util.Comparator;

public class OrganizmComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Organizm oo1 = (Organizm) o1;
        Organizm oo2 = (Organizm) o2;
        if (oo1.getIni() > oo2.getIni())
            return -1;
        else if (oo1.getIni() < oo2.getIni())
            return 1;
        return 0;
    }
}
