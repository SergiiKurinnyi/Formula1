package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.util.List;

public interface ITableDescriptor {

    String getTableName();

    List<ColumnType> getColumns();

}

