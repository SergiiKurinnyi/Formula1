package si.kurinnyi.formula1.columns.impl;

import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.dataprocessor.LapDataUtil;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.columns.Column;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class BestLapColumn implements Column {

    private static final String LAP_TIME_TO_STRING_FORMAT = "%02d:%02d.%03d";
    private static final String NO_LAP_TIME = "--:--.---";
    private static final String TITLE = ColumnType.BEST_LAP.toString();

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getData(Racer racer) {
        if (LapDataUtil.bestLapAccessor.apply(racer) == ChronoUnit.FOREVER.getDuration()) {
            return NO_LAP_TIME;
        }
        return convertDurationToString(LapDataUtil.bestLapAccessor.apply(racer));
    }

    @Override
    public Comparator<Racer> getComparator() {
        return (Comparator.comparing(LapDataUtil.bestLapAccessor));
    }

    private String convertDurationToString(Duration lap) {
        return String.format(LAP_TIME_TO_STRING_FORMAT, lap.toMinutesPart(), lap.toSecondsPart(), lap.toMillisPart());
    }

}
