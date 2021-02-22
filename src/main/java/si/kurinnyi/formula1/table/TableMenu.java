package si.kurinnyi.formula1.table;

import si.kurinnyi.formula1.columns.ColumnType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class TableMenu implements BaseTableMenu {

    private final static List<TableType> TABLE_LIST = Arrays.asList(TableType.values());

    private final static String INIT_MESSAGE = "Please type in a table name from the list: ";
    private final static String NO_SUCH_TABLE = "Table not found. Please type in a table name from the list: ";
    private final static String NO_SUCH_COLUMN = "Column not found.";
    private final static String CHOOSE_COLUMN = "Choose sorting column from the list: ";
    private final static String CHOOSE_SORT_ORDER = "Choose sorting order: ";
    private final static String ASCENDING = "Ascending";
    private final static String DESCENDING = "Descending";

    @Override
    public TableType getTableType() throws IOException {
        System.out.println(INIT_MESSAGE + TABLE_LIST);
        String inputTableTypes;
        List<TableType> matchingTable;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            inputTableTypes = bufferedReader.readLine();
            String finalInputTableTypes = inputTableTypes;
            if (TABLE_LIST.stream().anyMatch(table -> table.toString().equalsIgnoreCase(finalInputTableTypes))) {
                break;
            }
            System.out.println(NO_SUCH_TABLE + TABLE_LIST);
        }
        String finalInputTableTypes = inputTableTypes;
        matchingTable = TABLE_LIST.stream()
                .filter(table -> table.toString().equalsIgnoreCase(finalInputTableTypes))
                .collect(toList());

        return matchingTable.get(0);
    }

    @Override
    public ColumnType getSortingColumn(TableType tableType) throws IOException {
        List<ColumnType> matchingColumn;
        String inputSortingColumn;
        BufferedReader columnReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println(CHOOSE_COLUMN + tableType.getColumnList());
            inputSortingColumn = columnReader.readLine();
            String finalInputSortingColumn = inputSortingColumn;
            if (tableType.getColumnList().stream()
                    .anyMatch(column -> column.toString().equalsIgnoreCase(finalInputSortingColumn))) {
                break;
            }
            System.out.println(NO_SUCH_COLUMN);
        }
        String finalInputSortingColumn = inputSortingColumn;
        matchingColumn = Arrays.stream(ColumnType.values())
                .filter(column -> column.toString().equalsIgnoreCase(finalInputSortingColumn))
                .collect(toList());

        return matchingColumn.get(0);
    }

    @Override
    public Boolean isSortingReversed() throws IOException {
        String inputSortingDirection;
        try (BufferedReader sortReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println(CHOOSE_SORT_ORDER + ASCENDING + " or " + DESCENDING);
                inputSortingDirection = sortReader.readLine();
                if (inputSortingDirection.equalsIgnoreCase(ASCENDING)) {

                    return false;
                }
                if (inputSortingDirection.equalsIgnoreCase(DESCENDING)) {

                    return true;
                }
            }
        }
    }
}
