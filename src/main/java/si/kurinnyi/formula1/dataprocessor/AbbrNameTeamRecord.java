package si.kurinnyi.formula1.dataprocessor;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbbrNameTeamRecord that = (AbbrNameTeamRecord) o;
        return getAbbr().equals(that.getAbbr()) && getName().equals(that.getName()) && getTeam().equals(that.getTeam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbbr(), getName(), getTeam());
    }

}
