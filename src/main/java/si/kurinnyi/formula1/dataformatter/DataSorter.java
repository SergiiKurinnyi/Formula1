package si.kurinnyi.formula1.dataformatter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import si.kurinnyi.formula1.racer.Racer;

public class DataSorter {
	
	Racer racer;
	
	public List<Racer> sortByLapTime(List<Racer> racers) {
		 return racers.stream()
				.sorted(Comparator.comparing(Racer::getLapTime))
				.collect(Collectors.toList());
	}
	
	public List<Racer> sortByName(List<Racer> racers) {
		return racers.stream()
				.sorted(Comparator.comparing(Racer::getName))
				.collect(Collectors.toList());
	}
	
}
