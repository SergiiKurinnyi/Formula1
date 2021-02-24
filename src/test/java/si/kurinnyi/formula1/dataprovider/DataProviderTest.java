package si.kurinnyi.formula1.dataprovider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import si.kurinnyi.formula1.racer.Racer;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataProviderTest {         //NOT FINISHED, SHOULD BE HEAVILY MOCKED ... WILL DO

    DataProvider dataProvider = new DataProvider();

    @Test
    void dataProviderShouldReturnRacerListWhenNoArguments() throws URISyntaxException {
        List<Duration> lapDurationGRW = new ArrayList<>();
        lapDurationGRW.add(Duration.between(LocalTime.parse("12:26:33.003"), LocalTime.parse("12:28:00.792")));
        List<Duration> lapDurationCSM = new ArrayList<>();
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:26:32.442"), LocalTime.parse("12:27:58.645")));
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:28:59.476"), LocalTime.parse("12:30:26.054")));

        List<Racer> expected = new ArrayList<>();
        expected.add(new Racer("Carlos Sainz", "MCLAREN RENAULT", lapDurationCSM));
        expected.add(new Racer("George Russell", "WILLIAMS MERCEDES", lapDurationGRW));

        List<Racer> actual = dataProvider.provideData();
        assertEquals(expected, actual);
    }

}
