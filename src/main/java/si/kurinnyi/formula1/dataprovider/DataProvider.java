package si.kurinnyi.formula1.dataprovider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import si.kurinnyi.formula1.dataformatter.DataSorter;
import si.kurinnyi.formula1.dataformatter.RacerFormatter;
import si.kurinnyi.formula1.dataparser.DataToMapParser;
import si.kurinnyi.formula1.filereader.FileReaderImpl;
import si.kurinnyi.formula1.racer.AbbrTimePoints;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.reportformatter.ReportFormatter;
import si.kurinnyi.formula1.tabledescriptor.ColumnType;
import si.kurinnyi.formula1.tabledescriptor.TableType;

public class DataProvider {

	private static List<Racer> racerList = new ArrayList<>();

	private final FileReaderImpl dataReaderImpl = new FileReaderImpl();
	private final DataToMapParser dataToMapParser = new DataToMapParser();
	private final RacerFormatter formatter = new RacerFormatter();
	private final DataSorter dataSorter = new DataSorter();
	private final ReportFormatter reportFormatter = new ReportFormatter();

	public void newprovideData() throws URISyntaxException, IOException {
		Path abbrPath = Paths.get(getClass().getClassLoader()
				.getResource("abbreviations-new.txt").toURI());
		Path startTimePath = Paths.get(getClass().getClassLoader().getResource("start-new.log").toURI());
		Path endTimePath = Paths.get(getClass().getClassLoader().getResource("end-new.log").toURI());

		List<String> rawAbbr = dataReaderImpl.readFile(abbrPath);
		List<String> rawStart = dataReaderImpl.readFile(startTimePath);
		List<String> rawEnd = dataReaderImpl.readFile(endTimePath);

		Map<String, String> abbrToName = dataToMapParser.parseAbbreviationToName(rawAbbr);
		Map<String, String> abbrToTeam = dataToMapParser.parseAbbreviationToTeam(rawAbbr);
		Map<String, List<AbbrTimePoints>> newAbbrToAbbrStartTimePoints = dataToMapParser.newAbbrToTimePoints(rawStart);
		Map<String, List<AbbrTimePoints>> newAbbrToAbbrEndTimePoints = dataToMapParser.newAbbrToTimePoints(rawEnd);
		Map<String, List<LocalTime>> abbrToLaps = dataToMapParser.newAbbrToLaps(
				newAbbrToAbbrStartTimePoints, newAbbrToAbbrEndTimePoints);

		racerList = formatter.newformatRacer(abbrToName, abbrToTeam, abbrToLaps);
	}

	public void showRacerNameTable(TableType tableType, ColumnType columnTypeSort, String sortDirection) {

		List<String> report = new ArrayList<>();

		if (tableType == TableType.RACER_NAME) {
			List<Racer> racerNameList = dataSorter.sortByName(racerList);
			report = reportFormatter.formatTable(racerNameList, tableType);
		}

		if (tableType == TableType.LAP_COUNT) {
			List<Racer> racerLapCountList = dataSorter.sortByLapCount(racerList);
			report = reportFormatter.formatTable(racerLapCountList, tableType);
		}

		if (tableType == TableType.BEST_LAP) {
			List<Racer> racerBestLapList = dataSorter.sortByBestLap(racerList);
			report = reportFormatter.formatTable(racerBestLapList, tableType);
		}

		if (tableType == TableType.AVG_LAP_TIME) {
			List<Racer> racerAvgLapList = dataSorter.sortByAvgLap(racerList);
		//	racerList = dataSorter.sortByName(racerList);
		//	Collections.reverse(racerList);
			report = reportFormatter.formatTable(racerAvgLapList, tableType);
		}

		if(tableType == TableType.TOTAL_TIME) {
			List<Racer> totalTimeRacerList = dataSorter.sortByTotalTime(racerList);
		//	racerList = dataSorter.sortByName(racerList);
		//	Collections.reverse(racerList);
			report = reportFormatter.formatTable(totalTimeRacerList, tableType);
		}

		report.forEach(System.out::println);
	}

}
