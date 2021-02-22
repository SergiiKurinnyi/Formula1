package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TableDescriptor {

    private final String tableName;
    private final List<ColumnType> columns;

    public TableDescriptor(String tableName, ColumnType... columns) {
        this.tableName = tableName;
        this.columns = Arrays.asList(columns);
    }

    public String getTableName() {
        return  tableName;
    }

    public List<ColumnType> getColumns() {
        return Collections.unmodifiableList(columns);
    }

}
