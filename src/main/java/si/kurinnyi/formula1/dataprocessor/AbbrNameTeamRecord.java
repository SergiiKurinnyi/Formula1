package si.kurinnyi.formula1.dataprocessor;

public class AbbrNameTeamRecord {

    private final String abbr;
    private final String name;
    private final String team;

    public AbbrNameTeamRecord(String abbr, String name, String team) {
        this.abbr = abbr;
        this.name = name;
        this.team = team;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

}
