package si.kurinnyi.formula1.dataformatter;

import si.kurinnyi.formula1.racer.Racer;

import java.time.Duration;

public interface IDataSorter {

    Duration getAvgTime(Racer racer);

    Duration getBestLap(Racer racer);

    Duration getTotalTime(Racer racer);

}
