package si.kurinnyi.formula1;

import java.io.IOException;
import java.net.URISyntaxException;

import si.kurinnyi.formula1.dataprovider.DataProvider;
import si.kurinnyi.formula1.tabledescriptor.TableType;

public class FormulaOneApp {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		final DataProvider dataProvider = new DataProvider();

		dataProvider.newprovideData();
		dataProvider.showRacerNameTable(TableType.RACER_NAME_TABLE);
		dataProvider.showRacerNameTable(TableType.LAP_COUNT_TABLE);
		dataProvider.showRacerNameTable(TableType.BEST_LAP_TABLE);
		dataProvider.showRacerNameTable(TableType.AVG_LAP_TIME_TABLE);
		dataProvider.showRacerNameTable(TableType.TOTAL_TIME);

	}

}
