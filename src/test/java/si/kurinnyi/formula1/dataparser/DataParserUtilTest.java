package si.kurinnyi.formula1.dataparser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import si.kurinnyi.formula1.dataprocessor.AbbrNameTeamRecord;
import si.kurinnyi.formula1.dataprocessor.AbbrTimeRecord;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataParserUtilTest {

    @Test
    void abbrNameTeamParserShouldReturnAbbrNameTeamRecordList() {
        List<AbbrNameTeamRecord> expected = new ArrayList<>();
        expected.add(new AbbrNameTeamRecord("NHR", "Nico Hulkenberg", "RENAULT"));
        expected.add(new AbbrNameTeamRecord("SVF", "Sebastian Vettel", "FERRARI"));

        List<String> testSource = new ArrayList<>();
        testSource.add("NHR_Nico Hulkenberg_RENAULT");
        testSource.add("SVF_Sebastian Vettel_FERRARI");

        List<AbbrNameTeamRecord> actual = DataParserUtil.abbrNameTeamParser.apply(testSource);
        assertEquals(expected, actual);
    }

    @Test
    void abbrTimePointsParserShouldReturnAbbrTimeRecordList() {
        List<AbbrTimeRecord> expected = new ArrayList<>();
        expected.add(new AbbrTimeRecord("SVF", LocalTime.parse("12:02:58.917")));
        expected.add(new AbbrTimeRecord("NHR", LocalTime.parse("12:02:49.914")));

        List<String> testSource = new ArrayList<>();
        testSource.add("SVF2018-05-24_12:02:58.917");
        testSource.add("NHR2018-05-24_12:02:49.914");

        List<AbbrTimeRecord> actual = DataParserUtil.abbrTimePointsParser.apply(testSource);
        assertEquals(expected, actual);
    }

    @Test
    void startEndTimeConcatenatorShouldReturnMapStringToLocalTimeListWhenArgumentIs2AbbrTimeRecordLists() {
        Map<String, List<LocalTime>> expected = new HashMap<>();
        List<LocalTime> timeList = new ArrayList<>();
        timeList.add(LocalTime.parse("12:02:49.914"));
        timeList.add(LocalTime.parse("12:17:30.931"));
        expected.put("NHR", timeList);

        List<AbbrTimeRecord> testSource1 = new ArrayList<>();
        testSource1.add(new AbbrTimeRecord("NHR", LocalTime.parse("12:02:49.914")));
        List<AbbrTimeRecord> testSource2 = new ArrayList<>();
        testSource2.add(new AbbrTimeRecord("NHR", LocalTime.parse("12:17:30.931")));

        Map<String, List<LocalTime>> actual = DataParserUtil.startEndTimeConcatenator.apply(testSource1, testSource2);
        assertEquals(expected, actual);
    }

    @Test
    void lapListBuilderShouldDurationListWhenArgumentIsLocalTimeList() {
        List<Duration> expected = new ArrayList<>();
        expected.add(Duration.between(LocalTime.parse("12:02:49.914"), LocalTime.parse("12:17:30.931")));

        List<LocalTime> timeList = new ArrayList<>();
        timeList.add(LocalTime.parse("12:02:49.914"));
        timeList.add(LocalTime.parse("12:17:30.931"));

        List<Duration> actual = DataParserUtil.lapListBuilder.apply(timeList);
        assertEquals(expected, actual);
    }

}
