package si.kurinnyi.formula1.abbreviationreader;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.time.Duration;
import java.time.LocalTime;

public class DataToMapParser {

	private static final String DELIMITER = "_";

	public Map<String, String> parseAbbreviationToName(List<String> abbreviations) {
		return abbreviations.stream()
				.map(line -> line.split(DELIMITER))
				.collect(Collectors.toMap(element -> element[0], element -> element[1]));
	}

	public Map<String, String> parseAbbreviationToTeam(List<String> abbreviations) {
		return abbreviations.stream()
				.map(line -> line.split(DELIMITER))
				.collect(Collectors.toMap(element -> element[0], element -> element[2]));
	}

	public Map<String, Duration> parseAbbreviationToLapTime(List<String> start, List<String> end) {
		Map<String, LocalTime> abbreviationToStart;
		abbreviationToStart = start.stream()
				.collect(Collectors.toMap(
						element -> element.substring(0,3), 
						element -> LocalTime.parse(element.substring(element.indexOf(DELIMITER)+1))
						));

		Map<String, LocalTime> abbreviationToEnd;
		abbreviationToEnd = end.stream()
				.collect(Collectors.toMap(
						element -> element.substring(0,3), 
						element -> LocalTime.parse(element.substring(element.indexOf(DELIMITER)+1))
						));

		return abbreviationToStart.entrySet().stream()
				.collect(Collectors.toMap(
						Map.Entry::getKey, element -> Duration.between(element.getValue(), 
								abbreviationToEnd.get(element.getKey()))));
	}
	
}
