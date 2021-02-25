package si.kurinnyi.formula1.dataprocessor;

import java.time.LocalTime;
import java.util.Objects;

public class AbbrTimeRecord {
    private final String abbr;
    private final LocalTime time;

    public AbbrTimeRecord(String abbr, LocalTime time) {
        this.abbr = abbr;
        this.time = time;
    }

    public String getAbbr() {
        return abbr;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbbrTimeRecord that = (AbbrTimeRecord) o;
        return getAbbr().equals(that.getAbbr()) && getTime().equals(that.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAbbr(), getTime());
    }

}
