package si.kurinnyi.formula1.dataprovider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import si.kurinnyi.formula1.abbreviationreader.DataToMapParser;
import si.kurinnyi.formula1.dataformatter.DataSorter;
import si.kurinnyi.formula1.dataformatter.RacerFormatter;
import si.kurinnyi.formula1.filereader.FileReaderImpl;
import si.kurinnyi.formula1.racer.Racer;
import si.kurinnyi.formula1.reportformatter.ReportFormatter;

public class DataProvider {

	private final FileReaderImpl dataReaderImpl = new FileReaderImpl();
	private final DataToMapParser dataToMapParser = new DataToMapParser();
	private final RacerFormatter formatter = new RacerFormatter();
	private final DataSorter dataSorter = new DataSorter();
	private final ReportFormatter reportFormatter = new ReportFormatter();

	public List<String> provideData() throws URISyntaxException, IOException {
		Path abbrPath = Paths.get(getClass().getClassLoader()
				.getResource("abbreviations.txt").toURI());
		Path startTimePath = Paths.get(getClass().getClassLoader().getResource("start.log").toURI());
		Path endTimePath = Paths.get(getClass().getClassLoader().getResource("end.log").toURI());
		
		List<String> rawAbbr = dataReaderImpl.readFile(abbrPath);
		List<String> rawStart = dataReaderImpl.readFile(startTimePath);
		List<String> rawEnd = dataReaderImpl.readFile(endTimePath);
		
		Map<String, String> abbrToName = dataToMapParser.parseAbbreviationToName(rawAbbr);
		Map<String, String> abbrToTeam = dataToMapParser.parseAbbreviationToTeam(rawAbbr);
		Map<String, Duration> abbrToLapTime = dataToMapParser.parseAbbreviationToLapTime(rawStart, rawEnd);
		List<Racer> racerData = formatter.formatRacer(abbrToName, abbrToTeam, abbrToLapTime);
		
		List<Racer> racerDataSortedByLapTime = dataSorter.sortByLapTime(racerData);
		List<Racer> racerDataSortedByName = dataSorter.sortByName(racerData);
		List<String> raceReport = reportFormatter.formatReport(racerDataSortedByLapTime);

		return raceReport;
	}

}
