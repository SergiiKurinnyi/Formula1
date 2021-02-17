package si.kurinnyi.formula1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import si.kurinnyi.formula1.dataprovider.DataProvider;
import si.kurinnyi.formula1.tabledescriptor.Column;
import si.kurinnyi.formula1.tabledescriptor.ColumnType;
import si.kurinnyi.formula1.tabledescriptor.TableType;

public class FormulaOneApp {

	public final static String INIT_MESSAGE = "Please type in a table name from the list: ";
	public final static String NO_SUCH_TABLE = "Table not found. Please type in a table name from the list: ";
	public final static String NO_SUCH_COLUMN = "Column not found.";

	public static void main(String[] args) throws IOException, URISyntaxException {
		final DataProvider dataProvider = new DataProvider();
		List<TableType> tablesList = Arrays.asList(TableType.values());

		System.out.println(INIT_MESSAGE + tablesList);
		String inputTableTypes = "";
		String inputSortingColumn = "";
		String inputSortingDirection = "";
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
			while(true) {
				inputTableTypes = bufferedReader.readLine();
				String finalInputTableTypes = inputTableTypes;
				if (tablesList.stream().anyMatch(table -> table.toString().equalsIgnoreCase(finalInputTableTypes))) {
					break;}
				System.out.println(NO_SUCH_TABLE + tablesList);
			}
			String finalInputTableTypes = inputTableTypes;
		List<TableType> matchingTable = tablesList.stream()
				.filter(table -> table.toString().equalsIgnoreCase(finalInputTableTypes))
				.collect(Collectors.toList());
		while(true) {
			System.out.println("Choose sorting column from the list: " + matchingTable.get(0).getColumnList());
			inputSortingColumn = bufferedReader.readLine();
			String finalInputSortingColumn = inputSortingColumn;
			if (matchingTable.get(0).getColumnList().stream()
					.anyMatch(column -> column.toString().equalsIgnoreCase(finalInputSortingColumn))) {
				break;}
			System.out.println(NO_SUCH_COLUMN);
			}
		String finalInputSortingColumn = inputSortingColumn;
		List<ColumnType> matchingColumn = Arrays.stream(ColumnType.values())
				.filter(column -> column.toString().equalsIgnoreCase(finalInputSortingColumn))
				.collect(Collectors.toList());




//		if (!tablesList.toString().toLowerCase().contains(inputTableTypes.toLowerCase())) {
//			System.out.println("No such table");
//		}
//
//		if (inputTableTypes.equalsIgnoreCase(TableType.RACER_NAME.getTitle())) {
//			System.out.println("Choose sorting direction: Ascending or Descending");
//			inputSortingDirection = bufferedReader.readLine();
//		}
//
//		if (inputTableTypes.equalsIgnoreCase(TableType.LAP_COUNT.getTitle())) {
//			System.out.println("Choose sorting column from the list: " + TableType.LAP_COUNT.getColumnList());
//			inputSortingColumn = bufferedReader.readLine();
//			System.out.println("Choose sorting direction: Ascending or Descending");
//			inputSortingDirection = bufferedReader.readLine();
//		}
//
//		if (inputTableTypes.equalsIgnoreCase(TableType.BEST_LAP.getTitle())) {
//			System.out.println("Choose sorting column from the list: " + TableType.BEST_LAP.getColumnList());
//			inputSortingColumn = bufferedReader.readLine();
//			System.out.println("Choose sorting direction: Ascending or Descending");
//			inputSortingDirection = bufferedReader.readLine();
//		}
//
//		if (inputTableTypes.equalsIgnoreCase(TableType.AVG_LAP_TIME.getTitle())) {
//			System.out.println("Choose sorting column from the list: " + TableType.AVG_LAP_TIME.getColumnList());
//			inputSortingColumn = bufferedReader.readLine();
//			System.out.println("Choose sorting direction: Ascending or Descending");
//			inputSortingDirection = bufferedReader.readLine();
//		}
//
//		if (inputTableTypes.equalsIgnoreCase(TableType.TOTAL_TIME.getTitle())) {
//			System.out.println("Choose sorting column from the list: " + TableType.TOTAL_TIME.getColumnList());
//			inputSortingColumn = bufferedReader.readLine();
//			System.out.println("Choose sorting direction: Ascending or Descending");
//			inputSortingDirection = bufferedReader.readLine();
//		}


		}




		dataProvider.newprovideData();
//		dataProvider.showRacerNameTable(TableType.RACER_NAME_TABLE);
//		dataProvider.showRacerNameTable(TableType.LAP_COUNT_TABLE);
//		dataProvider.showRacerNameTable(TableType.BEST_LAP_TABLE);
//		dataProvider.showRacerNameTable(TableType.AVG_LAP_TIME_TABLE);
//		dataProvider.showRacerNameTable(TableType.TOTAL_TIME);

	}

//	private String getSortDirection() throws IOException {
//		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
//			while (true) {
//				System.out.println("Choose sorting direction: Ascending or Descending");
//				String inputSortingDirection = bufferedReader.readLine();
//				String finalInputSortingColumn = inputSortingColumn;
//				if (matchingTable.get(0).getColumnList().stream()
//						.anyMatch(column -> column.toString().equalsIgnoreCase(finalInputSortingColumn))) {
//					break;
//				}
//				System.out.println(NO_SUCH_COLUMN);
//			}
//		}
//
//		return null;
//	}

}
