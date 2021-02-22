package si.kurinnyi.formula1.dataprovider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Map;

import si.kurinnyi.formula1.racer.RacerFormatter;
import si.kurinnyi.formula1.dataparser.DataToMapParser;
import si.kurinnyi.formula1.filereader.FileReaderImpl;
import si.kurinnyi.formula1.dataformatter.AbbrTimePoints;
import si.kurinnyi.formula1.racer.Racer;

public class DataProvider implements IDataProvider {

    private final FileReaderImpl dataReaderImpl = new FileReaderImpl();
    private final DataToMapParser dataToMapParser = new DataToMapParser();
    private final RacerFormatter formatter = new RacerFormatter();

    public List<Racer> provideData() throws URISyntaxException, IOException {
        Path abbrPath = Paths.get(getClass().getClassLoader()
                .getResource("abbreviations-new.txt").toURI());
        Path startTimePath = Paths.get(getClass().getClassLoader().getResource("start-new.log").toURI());
        Path endTimePath = Paths.get(getClass().getClassLoader().getResource("end-new.log").toURI());

        List<String> rawAbbr = dataReaderImpl.readFile(abbrPath);
        List<String> rawStart = dataReaderImpl.readFile(startTimePath);
        List<String> rawEnd = dataReaderImpl.readFile(endTimePath);

        Map<String, String> abbrToName = dataToMapParser.parseAbbreviationToName(rawAbbr);
        Map<String, String> abbrToTeam = dataToMapParser.parseAbbreviationToTeam(rawAbbr);
        Map<String, List<AbbrTimePoints>> newAbbrToAbbrStartTimePoints = dataToMapParser.abbrToTimePoints(rawStart);
        Map<String, List<AbbrTimePoints>> newAbbrToAbbrEndTimePoints = dataToMapParser.abbrToTimePoints(rawEnd);
        Map<String, List<Duration>> abbrToLaps = dataToMapParser.abbrToLaps(
                newAbbrToAbbrStartTimePoints, newAbbrToAbbrEndTimePoints);

        return formatter.formatRacer(abbrToName, abbrToTeam, abbrToLaps);
    }

}
