package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.io.IOException;

public interface BaseTableMenu {

    TableType getTableType() throws IOException;

    ColumnType getSortingColumn(TableType tableType) throws IOException;

    Boolean isSortingReversed() throws IOException;

}
