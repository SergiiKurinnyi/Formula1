package si.kurinnyi.formula1.tabledescriptor;

import si.kurinnyi.formula1.racer.Racer;

import java.util.ArrayList;
import java.util.List;

public class TableDescriptorFactory implements TableDescriptor{

    ColumnFactory columnFactory = new ColumnFactory();

    @Override
    public String getTitle(TableType tableType) {
        return tableType.getTitle();
    }

    @Override
    public List<Column> getColumns(List<Racer> racerList, TableType tableType) {
        columnFactory.getColumn(racerList, tableType);
        return new ArrayList<>();
    }

    @Override
    public ColumnType getDefaultSortColumnType() {
        return null;
    }
}
