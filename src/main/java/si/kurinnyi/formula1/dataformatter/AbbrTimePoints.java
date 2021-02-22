package si.kurinnyi.formula1.dataformatter;

public class AbbrTimePoints {

    private final String abbr;
    private final String time;

    public AbbrTimePoints(String abbr, String time) {
        this.abbr = abbr;
        this.time = time;
    }

    public String getAbbr() {
        return abbr;
    }

    public String getTime() {
        return time;
    }

}
