package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.util.Arrays;
import java.util.List;

public enum TableType {
    RACER_NAME("Racers", Arrays.asList(
            ColumnType.NAME)),
    BEST_LAP("Best Laps", Arrays.asList(
            ColumnType.NAME, ColumnType.TEAM, ColumnType.BEST_LAP)),
    LAP_COUNT("Amount of Laps", Arrays.asList(
            ColumnType.NAME, ColumnType.TEAM, ColumnType.LAP_COUNT)),
    AVG_LAP_TIME("Average Lap Time", Arrays.asList(
            ColumnType.NAME, ColumnType.TEAM, ColumnType.AVG_LAP_TIME)),
    TOTAL_TIME("Racers Positions", Arrays.asList(
            ColumnType.NAME, ColumnType.TEAM, ColumnType.TOTAL_TIME));

    private final String title;
    private final List<ColumnType> columnList;

    TableType(String title, List<ColumnType> columnList) {
        this.title = title;
        this.columnList = columnList;
    }

    public String getTitle() {
        return title;
    }

    public List<ColumnType> getColumnList() {
        return columnList;
    }

    @Override
    public String toString() {
        return title;
    }

}
