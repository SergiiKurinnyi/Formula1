package si.kurinnyi.formula1.columns.impl;

import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.columns.Column;

import java.util.Comparator;

public class LapCountColumn implements Column {

    private static final String TITLE = ColumnType.LAP_COUNT.toString();

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getData(Racer racer) {
        if (racer.getLaps() == null) {
            return "0";
        }
        return String.valueOf(racer.getLaps().size());
    }

    @Override
    public Comparator<Racer> getComparator() {

        return Comparator.nullsLast(Comparator.comparing(racer -> {
                    if (racer.getLaps() == null) {
                        return 0;
                    }
                    return racer.getLaps().size();
                }));
    }

}
