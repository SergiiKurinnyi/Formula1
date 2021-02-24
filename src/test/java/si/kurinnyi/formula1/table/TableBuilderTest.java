package si.kurinnyi.formula1.table;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import si.kurinnyi.formula1.columns.ColumnFactory;
import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.racer.Racer;

public class TableBuilderTest {

    TableDescriptor descriptor;
    ColumnType sortingColumnType;
    ColumnFactory columnFactory;
    TableBuilder tableBuilder;
    List<Racer> racerList;
    Boolean reverseOrder;

    @BeforeEach
    void init() {
        columnFactory = new ColumnFactory();
        tableBuilder = new TableBuilder(columnFactory);
        List<Duration> lapDurationGRW = new ArrayList<>();
        lapDurationGRW.add(Duration.between(LocalTime.parse("12:26:33.003"), LocalTime.parse("12:28:00.792")));
        List<Duration> lapDurationCSM = new ArrayList<>();
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:26:32.442"), LocalTime.parse("12:27:58.645")));
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:28:59.476"), LocalTime.parse("12:30:26.054")));
        racerList = new ArrayList<>();
        racerList.add(new Racer("Carlos Sainz", "MCLAREN RENAULT", lapDurationCSM));
        racerList.add(new Racer("George Russell", "WILLIAMS MERCEDES", lapDurationGRW));
    }

    @Test
    void buildTableShouldReturnStringStartingWithTableNameWhenArgumentIsRacerListAndTableDescriptorAndColumnTypeAndOrderBoolean() {
        descriptor = new TableDescriptor(
                "Best Laps", ColumnType.ROW_NUM, ColumnType.NAME, ColumnType.TEAM, ColumnType.BEST_LAP);
        sortingColumnType = ColumnType.BEST_LAP;
        reverseOrder = false;

        String tableName = "Best Laps";
        String actual = tableBuilder.buildTable(racerList, descriptor, sortingColumnType, reverseOrder);
        assertThat(actual, startsWith(tableName));
    }

    @Test
    void buildTableShouldReturnStringsInReversedOrderContainingRacerNamesWhenArgumentIsReversedAndTableTypeIsRacers() {
        descriptor = new TableDescriptor(
                "Racers", ColumnType.ROW_NUM, ColumnType.NAME);
        sortingColumnType = ColumnType.NAME;
        reverseOrder = true;

        String racerName1 = "Carlos Sainz";
        String racerName2 = "George Russell";
        String actual = tableBuilder.buildTable(racerList, descriptor, sortingColumnType, reverseOrder);
        assertThat(actual, stringContainsInOrder(racerName2, racerName1));
    }

}
