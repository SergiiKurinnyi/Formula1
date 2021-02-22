package si.kurinnyi.formula1.racer;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IRacerFormatter {

    List<Racer> formatRacer(Map<String, String> abbrToName, Map<String, String> abbrToTeam,
                            Map<String, List<Duration>> abbrToLap);

}
