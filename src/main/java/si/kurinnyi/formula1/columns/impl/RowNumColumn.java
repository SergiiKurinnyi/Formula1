package si.kurinnyi.formula1.columns.impl;

import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.columns.Column;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class RowNumColumn implements Column {

    private static final String TITLE = ColumnType.ROW_NUM.toString();
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getData(Racer racer) {
        return String.format("%2d", counter.incrementAndGet());
    }

    @Override
    public Comparator<Racer> getComparator() {
        throw new IllegalArgumentException("This column cannot be sorted");
    }

}
