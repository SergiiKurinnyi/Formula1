package si.kurinnyi.formula1.reportformatter;

import static java.lang.String.format;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import si.kurinnyi.formula1.racer.Racer;

public class ReportFormatter {

	private static final String lineFormatPrefix = "%2s. %-";
	private static final String lineFormatDelimiter1 = "s|%-";
	private static final String lineFormatDelimiter2 = "s|%s";
	private static final String durationToStringFormat = "%d:%02d.%03d";
	private static final String spaceIndent = " ";
	private static final String minusSignIndent = "-";
	
	private List<String> finalReport = new ArrayList<String>();

	public List<String> formatReport(List<Racer> racers) {
		final int nameLineLength = getMaxNameLineLength(racers).get() + 1;
		final int teamLineLength = getMaxTeamLineLength(racers).get() + 1;

		for (int i = 0; i < racers.size(); i++) {
			String currentRacerName = racers.get(i).getName();
			String currentRacerTeam = racers.get(i).getTeam();
			String lapTime = convertDurationToString(racers.get(i).getLapTime());

				String currentLine = format(lineFormatPrefix + nameLineLength + lineFormatDelimiter1 
						+ teamLineLength + lineFormatDelimiter2, i + 1, 
						currentRacerName, currentRacerTeam, lapTime);
				finalReport.add(currentLine);
				if (i == 14) {
					finalReport.add(format("%" + currentLine.length() + "s", "").replace(spaceIndent, minusSignIndent));
				}
		}
		return finalReport;
	}

	private Optional<Integer> getMaxNameLineLength(List<Racer> racers) {
		return racers.stream()
				.map(x -> x.getName().length())
				.max((x1, x2) -> x1.compareTo(x2));
	}

	private Optional<Integer> getMaxTeamLineLength(List<Racer> racers) {
		return racers.stream()
				.map(x -> x.getTeam().length())
				.max((x1, x2) -> x1.compareTo(x2));
	}

	private String convertDurationToString(Duration lapTime) {
		long toMinutes = lapTime.toMinutesPart();
		long toSeconds = lapTime.toSecondsPart();
		long toMillis = lapTime.toMillisPart();
		return format(durationToStringFormat, toMinutes, toSeconds,toMillis);
	}

}
