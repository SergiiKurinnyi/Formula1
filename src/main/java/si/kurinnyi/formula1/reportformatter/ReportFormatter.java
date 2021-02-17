package si.kurinnyi.formula1.reportformatter;

import static java.lang.String.format;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.tabledescriptor.TableType;

public class ReportFormatter {

    private static final String LINE_FORMAT_PREFIX = "%2s. %-";
    private static final String LINE_FORMAT_DELIMITER1 = "s|%-";
    private static final String LINE_FORMAT_DELIMITER2 = "s|%s";
    private static final String MULT_LAPS_DELIMITER = " - ";
    private static final String LINE_SEPARATOR_PREFIX = "\r\n%";
    private static final String COLUMNS_LINE_SEPARATOR_PREFIX = "\r\n%2s. %-";
    private static final String LAP_TIME_TO_STRING_FORMAT = "%d:%02d.%03d";
    private static final String SPACE_INDENT = " ";
    private static final String MINUS_SIGN_INDENT = "-";

    public List<String> formatTable(List<Racer> racerList, TableType tableType) {
        List<String> result;
        final int nameLineLength = getMaxNameLineLength(racerList).get() + 4;
        final int teamLineLength = getMaxTeamLineLength(racerList).get() + 1;

        AtomicInteger atomicCounter = new AtomicInteger(-1);

        result = racerList.stream()
                .map(element -> formatLine(tableType, atomicCounter, element, nameLineLength, teamLineLength))
                .collect(Collectors.toList());

        return result;
    }

    private String formatLine(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength, int teamLineLength) {
        StringBuilder lineBuilder = new StringBuilder();
        if (counter.get() == -1) {
            counter.set(0);
            lineBuilder.append(tableType.getTitle());
        }
        if (tableType == TableType.RACER_NAME_TABLE) {

            return lineBuilder.append(
                    formatNamesTableString(tableType, counter, racer, nameLineLength)).toString();
        }

        if (tableType == TableType.LAP_COUNT_TABLE) {
			return lineBuilder.append(
			        formatLapCountTableString(tableType, counter, racer, nameLineLength, teamLineLength)).toString();
        }

        if (tableType == TableType.BEST_LAP_TABLE) {
            return lineBuilder.append(
                    formatBestLapTableString(tableType, counter, racer, nameLineLength, teamLineLength)).toString();
        }

        if (tableType == TableType.AVG_LAP_TIME_TABLE) {
            return lineBuilder.append(
                    formatAvgTimeTableString(tableType, counter, racer, nameLineLength, teamLineLength)).toString();
        }

        if(tableType == TableType.TOTAL_TIME) {
            return lineBuilder.append(
                    formatTotalTimeTableString(tableType, counter, racer, nameLineLength, teamLineLength)).toString();
        }

        return lineBuilder.toString();
    }

    private String formatNamesTableString(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength) {
        StringBuilder stringBuilder = new StringBuilder();
        if (counter.get() == 0) {
            counter.set(1);
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + nameLineLength + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append(format("\r\n%2s. %s", "No", tableType.getColumnList().get(0)));
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + nameLineLength + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append("\r\n");
        }
        return stringBuilder.append(format("%2s. %s", counter.getAndIncrement(), racer.getName())).toString();

    }

    private String formatLapCountTableString(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength, int teamLineLength) {
        int lineLength = 3 + nameLineLength + teamLineLength + tableType.getColumnList().get(2).toString().length();
        StringBuilder stringBuilder = new StringBuilder();
        if (counter.get() == 0) {
            counter.set(1);
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + lineLength + "s", "").
                    replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append(format(COLUMNS_LINE_SEPARATOR_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1 +
                            teamLineLength + LINE_FORMAT_DELIMITER2, "No", tableType.getColumnList().get(0),
                    tableType.getColumnList().get(1), tableType.getColumnList().get(2)));
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + (lineLength)  + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append("\r\n");
        }
        return stringBuilder.append(format(LINE_FORMAT_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1 +
                        teamLineLength + LINE_FORMAT_DELIMITER2, counter.getAndIncrement(), racer.getName(),
                racer.getTeam(), racer.getLaps().size())).toString();
    }

    private String formatBestLapTableString(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength, int teamLineLength) {
        int lineLength = 3 + nameLineLength + teamLineLength + formatTime(racer.getLaps().get(0)).length();
        StringBuilder stringBuilder = new StringBuilder();
        if (counter.get() == 0) {
            counter.set(1);
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + lineLength + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append(format(COLUMNS_LINE_SEPARATOR_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                            + teamLineLength + LINE_FORMAT_DELIMITER2, "No", tableType.getColumnList().get(0),
                    tableType.getColumnList().get(1), tableType.getColumnList().get(2)));
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + (lineLength)  + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append("\r\n");
        }
        return stringBuilder.append(format(LINE_FORMAT_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                        + teamLineLength + LINE_FORMAT_DELIMITER2, counter.getAndIncrement(), racer.getName(),
                racer.getTeam(), formatTime(racer.getLaps().get(0)))).toString();
    }

    private String formatAvgTimeTableString(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength, int teamLineLength) {
        int lineLength = 3 + nameLineLength + teamLineLength + tableType.getColumnList().get(2).toString().length();
        StringBuilder stringBuilder = new StringBuilder();
        if (counter.get() == 0) {
            counter.set(1);
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + lineLength + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append(format(COLUMNS_LINE_SEPARATOR_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                            + teamLineLength + LINE_FORMAT_DELIMITER2,
                    "No", tableType.getColumnList().get(0), tableType.getColumnList().get(1),
                    tableType.getColumnList().get(2)));
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + (lineLength) + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append("\r\n");
        }
        return stringBuilder.append(format(LINE_FORMAT_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                        + teamLineLength + LINE_FORMAT_DELIMITER2, counter.getAndIncrement(), racer.getName(),
                racer.getTeam(), formatTime(racer.getLaps().get(0)))).toString();
    }

    private String formatTotalTimeTableString(
            TableType tableType, AtomicInteger counter, Racer racer, int nameLineLength, int teamLineLength) {
        int lineLength = 3 + nameLineLength + teamLineLength + formatMultLapsLine(racer.getLaps()).length();
        StringBuilder stringBuilder = new StringBuilder();
        if (counter.get() == 0) {
            counter.set(1);
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + lineLength + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append(format(COLUMNS_LINE_SEPARATOR_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                                    + teamLineLength + LINE_FORMAT_DELIMITER2, "No", tableType.getColumnList().get(0),
                            tableType.getColumnList().get(1), tableType.getColumnList().get(2)));
            stringBuilder.append(format(LINE_SEPARATOR_PREFIX + (lineLength)  + "s", "")
                    .replace(SPACE_INDENT, MINUS_SIGN_INDENT));
            stringBuilder.append("\r\n");
        }
        if (counter.get() == 16) {
            stringBuilder.append(format("%" + lineLength + "s", "" + "\r\n").replace(SPACE_INDENT, MINUS_SIGN_INDENT));
        }

        return stringBuilder.append(format(LINE_FORMAT_PREFIX + (nameLineLength - 3) + LINE_FORMAT_DELIMITER1
                        + teamLineLength + LINE_FORMAT_DELIMITER2, counter.getAndIncrement(), racer.getName(),
                racer.getTeam(), formatMultLapsLine(racer.getLaps()))).toString();
    }

    private Optional<Integer> getMaxNameLineLength(List<Racer> racers) {
        return racers.stream()
                .map(x -> x.getName().length())
                .max(Integer::compareTo);
    }

    private Optional<Integer> getMaxTeamLineLength(List<Racer> racers) {
        return racers.stream()
                .map(x -> x.getTeam().length())
                .max(Integer::compareTo);
    }

    private String formatMultLapsLine(List<LocalTime> laps) {

        return laps.stream()
                .map(this::formatTime)
                .collect(Collectors.joining(MULT_LAPS_DELIMITER));
    }

    private String formatTime(LocalTime lapTime) {
        long toMinutes = lapTime.getMinute();
        long toSeconds = lapTime.getSecond();
        long toMillis = lapTime.getNano()/1000000;
        return format(LAP_TIME_TO_STRING_FORMAT, toMinutes, toSeconds, toMillis);
    }

}

