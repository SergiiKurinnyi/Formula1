package si.kurinnyi.formula1.racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacerFormatter implements IRacerFormatter {

    public List<Racer> formatRacer(Map<String, String> abbrToName, Map<String, String> abbrToTeam,
                                   Map<String, List<Duration>> abbrToLap) {
        return abbrToName
                .keySet()
                .stream()
                .map(element -> new Racer(abbrToName.get(element), abbrToTeam.get(element), abbrToLap.get(element)))
                .collect(Collectors.toList());
    }

}
