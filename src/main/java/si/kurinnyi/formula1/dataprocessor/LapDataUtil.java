package si.kurinnyi.formula1.dataprocessor;

import static java.time.Duration.ofMillis;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.function.Function;

import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.table.TableBuilder;

public class LapDataUtil {

    public static final Function<Racer, Duration> avgTimeAccessor = (racer) -> {
        if (racer.getLaps() != null && racer.getLaps().size() > 0) {

            return ofMillis((long) racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .average().getAsDouble());
        }

        return ChronoUnit.FOREVER.getDuration();
    };

    public static final Function<Racer, Duration> bestLapAccessor = (racer) -> {
        if (racer.getLaps() != null && racer.getLaps().size() > 0) {

            return ofMillis(racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .min().getAsLong());
        }

      return ChronoUnit.FOREVER.getDuration();
    };

    public static final Function<Racer, Duration> totalTimeAccessor = (racer) -> {
        if (racer.getLaps() != null && racer.getLaps().size() == TableBuilder.racerMaxLapCount) {

            return ofMillis(racer.getLaps().stream()
                    .mapToLong(Duration::toMillis)
                    .sum());
        }

        return ChronoUnit.FOREVER.getDuration();
    };

}
