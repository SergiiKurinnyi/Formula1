package si.kurinnyi.formula1.dataformatter;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import si.kurinnyi.formula1.racer.Racer;

public class RacerFormatter {

    public List<Racer> newformatRacer(Map<String, String> abbrToName, Map<String, String> abbrToTeam,
                                      Map<String, List<LocalTime>> abbrToLap) {
        return abbrToName
                .keySet()
                .stream()
                .map(element -> new Racer(abbrToName.get(element), abbrToTeam.get(element), abbrToLap.get(element)))
                .collect(Collectors.toList());
    }

}
