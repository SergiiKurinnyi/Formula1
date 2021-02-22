package si.kurinnyi.formula1.dataparser;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.time.LocalTime;
import java.util.stream.Stream;

import si.kurinnyi.formula1.dataprocessor.AbbrNameTeamRecord;
import si.kurinnyi.formula1.dataprocessor.AbbrTimeRecord;

public class DataParserUtil {

    private static final String DELIMITER = "_";

    public static final Function<List<String>, List<AbbrNameTeamRecord>> abbrNameTeamParser = list ->
        list.stream()
                .map(line -> line.split(DELIMITER))
                .map(element -> new AbbrNameTeamRecord(element[0], element[1], element[2]))
                .collect(toList());

    public static final Function<List<String>, List<AbbrTimeRecord>> abbrTimePointsParser = list -> list.stream()
            .map(line -> {
                String abbr = line.substring(0, 3);
                LocalTime timePoints = LocalTime.parse(line.substring(line.lastIndexOf(DELIMITER) + 1));
                return new AbbrTimeRecord(abbr, timePoints);
            })
            .collect(toList());

    public static final
    BiFunction<List<AbbrTimeRecord>, List<AbbrTimeRecord>, Map<String, List<LocalTime>>> startEndTimeConcatenator =
            (starts, finishes) -> Stream.concat(starts.stream(), finishes.stream())
            .collect(groupingBy(AbbrTimeRecord::getAbbr))
            .entrySet()
            .stream()
            .collect(toMap(
                    Map.Entry::getKey,
                    element -> element.getValue().stream().map(AbbrTimeRecord::getTime)
                    .collect(toList())));

    public static final Function<List<LocalTime>, List<Duration>> lapListBuilder = (timeList) -> {
        List<LocalTime> items = timeList.stream()
                .sorted(LocalTime::compareTo)
                .collect(toList());
        List<Duration> laps = new ArrayList<>();
        for (int i = 0; i < items.size(); i += 2) {
            if (i + 1 < items.size()) {
                laps.add(Duration.between(items.get(i), items.get(i + 1)));
            }
        }
        return laps;
    };

}
