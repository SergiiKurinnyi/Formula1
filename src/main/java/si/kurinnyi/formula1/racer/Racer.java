package si.kurinnyi.formula1.racer;

import java.time.Duration;

public class Racer {
	
	private String name;
	private String team;
	private Duration lapTime;

	public Racer( String name, String team, Duration lapTime) {
		this.name = name;
		this.team = team;
		this.lapTime = lapTime;
	}

	public String getName() {
		return name;
	}
	
	public String getTeam() {
		return team;
	}
	
	public Duration getLapTime() {
		return lapTime;
	}

}
