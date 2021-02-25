package si.kurinnyi.formula1.table;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

import si.kurinnyi.formula1.columns.Column;
import si.kurinnyi.formula1.columns.ColumnFactory;
import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TableBuilder {

    public static int racerMaxLapCount;
    public static int nameColumnLength;
    public static int teamColumnLength;

    private final static String DELIMITER = "|";
    private final static String NEXT_LINE = "\r\n";

    private final ColumnFactory columnFactory;

    public TableBuilder(ColumnFactory columnFactory) {
        this.columnFactory = columnFactory;
    }

    public String buildTable(
            List<Racer> racers, TableDescriptor descriptor, ColumnType sortingColumnType, Boolean reverseOrder) {

        racerMaxLapCount = getMaxLapCount(racers);
        nameColumnLength = getMaxNameLength(racers).get() + 1;
        teamColumnLength = getMaxTeamLength(racers).get() + 1;
        final int tableWidth = getTableWidth(descriptor);
        final String separationLineFormat = "%" + tableWidth + "s";
        final String separationLine = format(separationLineFormat, "").replaceAll(" ", "-") + NEXT_LINE;


        List<Column> columns = descriptor.getColumns().stream()
                .map(columnFactory::get)
                .collect(toList());

        Column sortingColumn = columnFactory.get(sortingColumnType);

        Comparator<Racer> comparator = sortingColumn.getComparator();
        if (reverseOrder) {
            comparator = comparator.reversed();
        }

        return descriptor.getTableName() + NEXT_LINE +
                separationLine +
                columns.stream()
                        .map(Column::getTitle)
                        .collect(joining(DELIMITER)) + NEXT_LINE +
                separationLine +
                racers.stream()
                        .sorted(comparator)
                        .map(racer -> columns.stream()
                                .map(line -> line.getData(racer)).collect(joining(DELIMITER))
                        )
                        .collect(joining(NEXT_LINE));
    }

    private int getMaxLapCount(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .mapToInt(racer -> racer.getLaps().size())
                .max().getAsInt();
    }

    private Optional<Integer> getMaxNameLength(List<Racer> racers) {

        return racers.stream()
                .map(x -> x.getName().length())
                .max(Integer::compareTo);
    }

    private Optional<Integer> getMaxTeamLength(List<Racer> racers) {

        return racers.stream()
                .map(x -> x.getTeam().length())
                .max(Integer::compareTo);
    }

    private int getTableWidth(TableDescriptor descriptor) {
        if (descriptor.getTableName().equals("Racers")) {
            return nameColumnLength + 2;
        }
        int lastColumnIndex = descriptor.getColumns().size() - 1;
        int lastColumnNameLength = (descriptor.getColumns().get(lastColumnIndex).toString().length());
        if (lastColumnNameLength < 9) {
            lastColumnNameLength = 9;
        }

        return nameColumnLength + teamColumnLength + lastColumnNameLength + 5;
    }

}
