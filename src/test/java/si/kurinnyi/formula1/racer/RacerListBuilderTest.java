package si.kurinnyi.formula1.racer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import si.kurinnyi.formula1.dataprocessor.AbbrNameTeamRecord;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RacerListBuilderTest {

    @Test
    void buildRacerShouldReturnRacerListIfArgumentsAreAbbrNameTeamRecordListAndMapStringToLocalTimeList() {
        List<Racer> expected = new ArrayList<>();
        List<Duration> lapDuration = new ArrayList<>();
        lapDuration.add(Duration.between(LocalTime.parse("12:02:49.914"), LocalTime.parse("12:17:30.931")));
        expected.add(new Racer("Nico Hulkenberg", "RENAULT", lapDuration));

        List<AbbrNameTeamRecord> nameTeamRecordList = new ArrayList<>();
        nameTeamRecordList.add(new AbbrNameTeamRecord("NHR", "Nico Hulkenberg", "RENAULT"));
        Map<String, List<LocalTime>> abbrToTimeMap = new HashMap<>();
        List<LocalTime> timeList = new ArrayList<>();
        timeList.add(LocalTime.parse("12:02:49.914"));
        timeList.add(LocalTime.parse("12:17:30.931"));
        abbrToTimeMap.put("NHR", timeList);

        List<Racer> actual = RacerListBuilder.buildRacer.apply(nameTeamRecordList, abbrToTimeMap);
        assertEquals(expected, actual);
    }

}
