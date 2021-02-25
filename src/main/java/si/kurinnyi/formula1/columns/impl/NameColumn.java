package si.kurinnyi.formula1.columns.impl;

import static java.lang.String.format;

import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.columns.Column;
import si.kurinnyi.formula1.table.TableBuilder;

import java.util.Comparator;

public class NameColumn implements Column {

    private static final String TITLE = ColumnType.NAME.toString();
    private static final String COLUMN_FORMAT = "%-" + TableBuilder.nameColumnLength +"s";

    @Override
    public String getTitle() {
        return format(COLUMN_FORMAT, TITLE);
    }

    @Override
    public String getData(Racer racer) {
        return format(COLUMN_FORMAT, racer.getName());
    }

    @Override
    public Comparator<Racer> getComparator() {
        return Comparator.comparing(Racer::getName);
    }

}
