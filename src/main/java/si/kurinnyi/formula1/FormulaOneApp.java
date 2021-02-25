package si.kurinnyi.formula1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import si.kurinnyi.formula1.columns.ColumnFactory;
import si.kurinnyi.formula1.dataprovider.DataProvider;
import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.table.*;

public class FormulaOneApp {

    public static void main(String[] args) throws IOException, URISyntaxException {

        final DataProvider dataProvider = new DataProvider();
        final TableDescriptorFactory descriptorFactory = new TableDescriptorFactory();
        final ColumnFactory columnFactory = new ColumnFactory();
        final TableBuilder tableBuilder = new TableBuilder(columnFactory);
        final TableMenu tableMenu = new TableMenu();
        final List<Racer> racers = dataProvider.provideData();

        TableType inputTable = tableMenu.getTableType();
        ColumnType sortingColumn = tableMenu.getSortingColumn(inputTable);
        Boolean reversedOrder = tableMenu.isSortingReversed();
        TableDescriptor descriptor = descriptorFactory.get(inputTable.getTitle());

        String table = tableBuilder.buildTable(racers, descriptor, sortingColumn, reversedOrder);
        System.out.println(table);
    }

}
