package si.kurinnyi.formula1.tabledescriptor;

import si.kurinnyi.formula1.racer.Racer;

import java.util.List;

public interface TableDescriptor {

    String getTitle(TableType tableType);

    List<Column> getColumns(List<Racer> racerList, TableType tableType);
    ColumnType getDefaultSortColumnType();

}
