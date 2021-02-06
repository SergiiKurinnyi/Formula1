package si.kurinnyi.formula1.dataformatter;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import si.kurinnyi.formula1.racer.Racer;

public class RacerFormatter {

	Racer racer;

	public List<Racer> formatRacer(Map<String, String> abbreviationToName, 
			Map<String, String> abbreviationToTeam, 
			Map<String, Duration> abbreviationToLapTime) {

		return abbreviationToName
				.keySet()
				.stream()
				.map(element -> new Racer (abbreviationToName.get(element), 
						abbreviationToTeam.get(element),
						abbreviationToLapTime.get(element)))
				.collect(Collectors.toList());
	}
}
