package si.kurinnyi.formula1.dataformatter;

import static java.util.stream.Collectors.toList;

import java.time.LocalTime;
import java.util.*;

import si.kurinnyi.formula1.racer.Racer;

public class DataSorter {

    public List<Racer> sortByName(List<Racer> racers) {

        return racers.stream()
                .sorted(Comparator.comparing(Racer::getName))
                .collect(toList());
    }

    public List<Racer> sortByAvgLap(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .sorted(Comparator.comparingLong(racer -> getAvgTime(racer).toNanoOfDay()))
                .map(racer -> new Racer(
                        racer.getName(), racer.getTeam(), Arrays.asList(getAvgTime(racer))))
                .filter(racer -> racer.getLaps() != null)
                .collect(toList());
    }

    public List<Racer> sortByBestLap(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .sorted(Comparator.comparingLong(racer -> getBestLap(racer).toNanoOfDay()))
                .map(racer -> new Racer(
                        racer.getName(), racer.getTeam(), Arrays.asList(getBestLap(racer))))
                .collect(toList());
    }

    public List<Racer> sortByTeam(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .sorted(Comparator.comparing(Racer::getTeam))
                .collect(toList());
    }

    public List<Racer> sortByLapCount(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .sorted(Comparator.comparingInt(racer -> racer.getLaps().size()))
                .collect(toList());
    }
    //All laps result
/*	public List<Racer> sortByTotalTime(List<Racer> racers) {
		List<Integer> lapVariations = getLapCount(racers);
		List<Racer> resultList = new ArrayList<>();

		for (int i = lapVariations.size(); i > 0; i--)	{
			resultList.addAll(lapTimesOnLapCount(racers, i));
		}

		return resultList;
	}*/

    //Only racers with max lap count
    public List<Racer> sortByTotalTime(List<Racer> racers) {
        List<Integer> lapVariations = getLapCount(racers);

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .filter(racer -> racer.getLaps().size() == lapVariations.get(0))
                .sorted(Comparator.comparing(this::getTotalLapsTime))
                .collect(toList());
    }

    private LocalTime getAvgTime(Racer racer) {
        OptionalDouble avg = racer.getLaps().stream()
                .mapToLong(LocalTime::toNanoOfDay)
                .average();

        return LocalTime.ofNanoOfDay((long) avg.getAsDouble());
    }

    private LocalTime getBestLap(Racer racer) {
        OptionalLong bestLap = racer.getLaps().stream()
                .mapToLong(LocalTime::toNanoOfDay)
                .min();

        return LocalTime.ofNanoOfDay(bestLap.getAsLong());
    }

    private List<Integer> getLapCount(List<Racer> racers) {

        return racers.stream()
                .filter(racer -> racer.getLaps() != null)
                .map(racer -> racer.getLaps().size())
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(toList());
    }

    private LocalTime getTotalLapsTime(Racer racer) {
        long totalLapsTime = racer.getLaps().stream()
                .mapToLong(LocalTime::toNanoOfDay)
                .sum();

        return LocalTime.ofNanoOfDay(totalLapsTime);
    }

/*	//For all laps result
	private List<Racer> lapTimesOnLapCount(List<Racer> racers, int counter) {
		return racers.stream()
				.filter(racer -> racer.getLaps() != null)
				.filter(racer -> racer.getLaps().size() == counter)
				.sorted(Comparator.comparing(racer -> getTotalLapsTime(racer)))
				//.map(racer -> new Racer(racer.getName(), racer.getTeam(), Arrays.asList(getTotalLapsTime(racer))))
				.collect(toList());
	}*/

}
