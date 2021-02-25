package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.util.HashMap;
import java.util.Map;

public class TableDescriptorFactory {

    private final Map<String, TableDescriptor> descriptors = new HashMap<>();

    public TableDescriptorFactory() {
        addDescriptor(new TableDescriptor("Racers", ColumnType.ROW_NUM, ColumnType.NAME));
        addDescriptor(new TableDescriptor(
                "Best Laps", ColumnType.ROW_NUM, ColumnType.NAME, ColumnType.TEAM, ColumnType.BEST_LAP));
        addDescriptor(new TableDescriptor(
                "Amount of Laps", ColumnType.ROW_NUM, ColumnType.NAME, ColumnType.TEAM, ColumnType.LAP_COUNT));
        addDescriptor(new TableDescriptor(
                "Average Lap Time",
                ColumnType.ROW_NUM, ColumnType.NAME, ColumnType.TEAM, ColumnType.AVG_LAP_TIME));
        addDescriptor(new TableDescriptor(
                "Racers Positions",
                ColumnType.ROW_NUM, ColumnType.NAME, ColumnType.TEAM, ColumnType.TOTAL_TIME));
    }

    private void addDescriptor(TableDescriptor descriptor) {
        descriptors.put(descriptor.getTableName(), descriptor);
    }

    public TableDescriptor get(String tableName) {
        if (descriptors.containsKey(tableName)) {
            return descriptors.get(tableName);
        }
        throw new IllegalArgumentException("No such table");
    }

}
