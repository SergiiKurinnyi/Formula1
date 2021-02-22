package si.kurinnyi.formula1.dataformatter;

import static java.time.Duration.ofMillis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.table.TableBuilder;

public class DataSorter implements IDataSorter {

    public Duration getAvgTime(Racer racer) {
        if (racer.getLaps() != null) {
            OptionalDouble avg = racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .average();

            return ofMillis((long) avg.getAsDouble());
        }

        return ChronoUnit.FOREVER.getDuration();
    }

    public Duration getBestLap(Racer racer) {
        if (racer.getLaps() != null) {
            OptionalLong bestLap = racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .min();

            return ofMillis(bestLap.getAsLong());
        }

        return ChronoUnit.FOREVER.getDuration();
    }

    public Duration getTotalTime(Racer racer) {
        if (racer.getLaps() != null && racer.getLaps().size() == TableBuilder.racerMaxLapCount) {
            long totalLapsTime = racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .sum();

            return ofMillis(totalLapsTime);
        }

        return ChronoUnit.FOREVER.getDuration();
    }

}
