package si.kurinnyi.formula1.tabledescriptor;

// defines columns like NameColumn, TeamColumn, etc
public enum ColumnType {
    NAME("Name"),
    TEAM("Team"),
    BEST_LAP("Best Lap"),
    LAP_COUNT("Amount of Laps"),
    AVG_LAP_TIME("Average Lap Time"),
    TIME_RESULT("Time Result");

    private String title;

    ColumnType(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }

}
