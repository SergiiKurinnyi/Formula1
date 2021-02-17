package si.kurinnyi.formula1.tabledescriptor;

import si.kurinnyi.formula1.racer.Racer;

import java.util.Comparator;

public interface Column {

    String getTitle();

    String getData(Racer racer);

    Comparator<Racer> getComparator();

}
