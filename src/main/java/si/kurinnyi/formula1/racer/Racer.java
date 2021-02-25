package si.kurinnyi.formula1.racer;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class Racer {

    private final String name;
    private final String team;
    private final List<Duration> laps;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Racer racer = (Racer) o;
        return getName().equals(racer.getName()) && getTeam().equals(racer.getTeam()) && getLaps().equals(racer.getLaps());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getTeam(), getLaps());
    }

}
