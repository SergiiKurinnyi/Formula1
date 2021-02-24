package si.kurinnyi.formula1.dataprocessor;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.table.TableBuilder;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LapDataUtilTest {

    Racer testRacer;
    Racer noLapsRacer;

    @BeforeEach
    void init() {
        List<Duration> lapDurationCSM = new ArrayList<>();
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:26:32.442"), LocalTime.parse("12:27:58.645")));
        lapDurationCSM.add(Duration.between(LocalTime.parse("12:28:59.476"), LocalTime.parse("12:30:26.054")));
        testRacer = new Racer("Carlos Sainz", "MCLAREN RENAULT", lapDurationCSM);
        List<Duration> emptyLaps = new ArrayList<>();
        noLapsRacer = new Racer("Carlos Sainz", "MCLAREN RENAULT", emptyLaps);
    }


    @Test
    void avgTimeAccessorShouldReturnAvgLapDurationWhenArgumentIsRacer() {
        Duration expected = Duration.parse("PT1M26.39S");
        Duration actual = LapDataUtil.avgTimeAccessor.apply(testRacer);

        assertEquals(expected, actual);
    }

    @Test
    void avgTimeAccessorShouldReturnChronoUnitForeverDurationIfArgumentHasEmptyLapDurationList() {
        Duration expected = ChronoUnit.FOREVER.getDuration();
        Duration actual = LapDataUtil.avgTimeAccessor.apply(noLapsRacer);

        assertEquals(expected, actual);
    }

    @Test
    void bestLapAccessorShouldReturnBestLapDurationWhenArgumentIsRacer() {
        Duration expected = Duration.parse("PT1M26.203S");
        Duration actual = LapDataUtil.bestLapAccessor.apply(testRacer);

        assertEquals(expected, actual);
    }

    @Test
    void bestLapAccessorShouldReturnChronoUnitForeverDurationIfArgumentHasEmptyLapDurationList() {
        Duration expected = ChronoUnit.FOREVER.getDuration();
        Duration actual = LapDataUtil.bestLapAccessor.apply(noLapsRacer);

        assertEquals(expected, actual);
    }

    @Test
    void totalTimeAccessorShouldReturnChronoUnitForeverDurationIfArgumentLapCountIsNotMaxCount() {
        TableBuilder.racerMaxLapCount = 99;
        Duration expected = ChronoUnit.FOREVER.getDuration();
        Duration actual = LapDataUtil.totalTimeAccessor.apply(testRacer);

        assertEquals(expected, actual);
    }

    @Test
    void totalTimeAccessorShouldReturnTotalLapsTimeWhenArgumentLapCountEqualsMaxCount() {
        TableBuilder.racerMaxLapCount = 2;
        Duration expected = Duration.parse("PT2M52.781S");
        Duration actual = LapDataUtil.totalTimeAccessor.apply(testRacer);

        assertEquals(expected, actual);
    }

    @Test
    void totalTimeAccessorShouldReturnChronoUnitForeverIfArgumentHasEmptyLapDurationList() {
        TableBuilder.racerMaxLapCount = 2;
        Duration expected = ChronoUnit.FOREVER.getDuration();
        Duration actual = LapDataUtil.totalTimeAccessor.apply(noLapsRacer);


        assertEquals(expected, actual);
    }


}
