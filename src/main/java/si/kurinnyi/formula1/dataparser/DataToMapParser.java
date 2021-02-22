package si.kurinnyi.formula1.dataparser;

import static java.util.stream.Collectors.toMap;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.LocalTime;
import java.util.stream.IntStream;

import si.kurinnyi.formula1.dataformatter.AbbrTimePoints;

public class DataToMapParser implements IDataToMapParser {

    private static final String DELIMITER = "_";

    public Map<String, String> parseAbbreviationToName(List<String> abbreviations) {
        return abbreviations.stream()
                .map(line -> line.split(DELIMITER))
                .collect(toMap(element -> element[0], element -> element[1]));
    }

    public Map<String, String> parseAbbreviationToTeam(List<String> abbreviations) {

        return abbreviations.stream()
                .map(line -> line.split(DELIMITER))
                .collect(toMap(element -> element[0], element -> element[2]));
    }

    public Map<String, List<AbbrTimePoints>> abbrToTimePoints(List<String> rawTime) {

        return rawTime.stream()
                .map(line -> {
                    String abbr = line.substring(0, 3);
                    String timePoints = line.substring(line.lastIndexOf(DELIMITER) + 1);
                    return new AbbrTimePoints(abbr, timePoints);
                })
                .collect(Collectors.groupingBy(AbbrTimePoints::getAbbr));
    }

    public Map<String, List<Duration>> abbrToLaps(
            Map<String, List<AbbrTimePoints>> abbrToStart, Map<String, List<AbbrTimePoints>> abbrToEnd) {

        return abbrToEnd.entrySet().stream()
                .collect(toMap(Map.Entry::getKey, value -> {
                    List<AbbrTimePoints> endTime = value.getValue();
                    List<AbbrTimePoints> startTime = abbrToStart.get(value.getKey());
                    if (endTime.isEmpty() || endTime == null) {
                        return new ArrayList<>();
                    }

                    return IntStream.range(0, endTime.size())
                            .mapToObj(index -> {
                                LocalTime start = LocalTime.parse(startTime.get(index).getTime());
                                LocalTime end = LocalTime.parse(endTime.get(index).getTime());

                                return Duration.between(start, end);
                            })
                            .collect(Collectors.toList());
                }));
    }

}
