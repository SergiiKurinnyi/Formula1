package si.kurinnyi.formula1.racer;

import java.time.LocalTime;
import java.util.List;

public class Racer {

	private String name;
	private String team;
	private List<LocalTime> laps;

	public Racer(String name, String team, List<LocalTime> laps) {
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

	public List<LocalTime> getLaps() {
		return laps;
	}

}
