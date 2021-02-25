package si.kurinnyi.formula1.columns;

public enum ColumnType {

    ROW_NUM("No"),
    NAME("Name"),
    TEAM("Team"),
    BEST_LAP("Best Lap"),
    LAP_COUNT("Amount of Laps"),
    AVG_LAP_TIME("Average Lap Time"),
    TOTAL_TIME("Total Time");

    private final String title;

    ColumnType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
