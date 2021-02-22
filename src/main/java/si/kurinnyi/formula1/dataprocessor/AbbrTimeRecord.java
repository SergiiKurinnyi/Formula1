package si.kurinnyi.formula1.dataprocessor;

import java.time.LocalTime;

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

}