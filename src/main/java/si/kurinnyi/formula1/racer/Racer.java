package si.kurinnyi.formula1.racer;

import java.time.Duration;
import java.util.List;

public class Racer {

    private String name;
    private String team;
    private List<Duration> laps;

    public Racer(String name, String team, List<Duration> laps) {
        this.name = name;
        this.team = team;
        this.laps = laps;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public List<Duration> getLaps() {
        return laps;
    }

}
