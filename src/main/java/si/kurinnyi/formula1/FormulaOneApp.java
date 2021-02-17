package si.kurinnyi.formula1;

import static java.util.stream.Collectors.toList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import si.kurinnyi.formula1.dataprovider.DataProvider;
import si.kurinnyi.formula1.tabledescriptor.ColumnType;
import si.kurinnyi.formula1.tabledescriptor.TableType;

public class FormulaOneApp {

    public final static String INIT_MESSAGE = "Please type in a table name from the list: ";
    public final static String NO_SUCH_TABLE = "Table not found. Please type in a table name from the list: ";
    public final static String NO_SUCH_COLUMN = "Column not found.";
    public final static String ASCENDING = "Ascending";
    public final static String DESCENDING = "Descending";
    public final static List<TableType> TABLE_LIST = Arrays.asList(TableType.values());

    public static void main(String[] args) throws IOException, URISyntaxException {
        final DataProvider dataProvider = new DataProvider();

        System.out.println(INIT_MESSAGE + TABLE_LIST);

        dataProvider.provideData();
        TableType inputTable = getTableType();
        ColumnType inputColumn = getSortingColumn(inputTable);
        String inputSortingDirection = getSortDirection();
        dataProvider.showRacerNameTable(inputTable, inputColumn, inputSortingDirection);
    }

    private static TableType getTableType() throws IOException {
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

    private static ColumnType getSortingColumn(TableType tableType) throws IOException {
        List<ColumnType> matchingColumn;
        String inputSortingColumn;
        BufferedReader columnReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Choose sorting column from the list: " + tableType.getColumnList());
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

    private static String getSortDirection() throws IOException {
        String inputSortingDirection;
        try (BufferedReader sortReader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Choose sorting direction: " + ASCENDING + " or " + DESCENDING);
                inputSortingDirection = sortReader.readLine();
                if (inputSortingDirection.equalsIgnoreCase(ASCENDING) ||
                        inputSortingDirection.equalsIgnoreCase(DESCENDING)) {

                    return inputSortingDirection;
                }
            }
        }
    }

}
