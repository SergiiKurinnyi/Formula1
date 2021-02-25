package si.kurinnyi.formula1.columns.impl;

import si.kurinnyi.formula1.columns.ColumnType;
import si.kurinnyi.formula1.dataprocessor.LapDataUtil;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.columns.Column;
import si.kurinnyi.formula1.table.TableBuilder;

import java.time.Duration;
import java.util.Comparator;

public class TotalTimeColumn implements Column {

    private static final String LAP_TIME_TO_STRING_FORMAT = "%02d:%02d.%03d";
    private static final String NO_LAP_TIME = "--:--.---";
    private static final String TITLE = ColumnType.BEST_LAP.toString();

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public String getData(Racer racer) {
        if (racer.getLaps() != null && racer.getLaps().size() == TableBuilder.racerMaxLapCount) {
            return convertDurationToString(LapDataUtil.totalTimeAccessor.apply(racer));
        }
        return NO_LAP_TIME;
    }

    @Override
    public Comparator<Racer> getComparator() {
        return Comparator.comparing(LapDataUtil.totalTimeAccessor);
    }

    private String convertDurationToString(Duration lap) {
        return String.format(LAP_TIME_TO_STRING_FORMAT, lap.toMinutesPart(), lap.toSecondsPart(), lap.toMillisPart());
    }

}
