package si.kurinnyi.formula1;

import java.io.IOException;
import java.net.URISyntaxException;

import si.kurinnyi.formula1.dataprovider.DataProvider;

public class FormulaOneApp {
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		final DataProvider dataProvider = new DataProvider();
		
		dataProvider.provideData().forEach(System.out::println);
	}

}
