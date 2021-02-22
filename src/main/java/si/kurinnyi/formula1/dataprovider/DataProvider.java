package si.kurinnyi.formula1.dataprovider;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import si.kurinnyi.formula1.dataprocessor.AbbrNameTeamRecord;
import si.kurinnyi.formula1.dataprocessor.AbbrTimeRecord;
import si.kurinnyi.formula1.racer.RacerListBuilder;
import si.kurinnyi.formula1.dataparser.DataParserUtil;
import si.kurinnyi.formula1.filereader.FileReaderUtil;
import si.kurinnyi.formula1.racer.Racer;

public class DataProvider implements BaseDataProvider {

    public List<Racer> provideData() throws URISyntaxException {
        Path abbrPath = Paths.get(getClass().getClassLoader()
                .getResource("abbreviations-new.txt").toURI());
        Path startTimePath = Paths.get(getClass().getClassLoader().getResource("start-new.log").toURI());
        Path endTimePath = Paths.get(getClass().getClassLoader().getResource("end-new.log").toURI());

        List<String> rawAbbr = FileReaderUtil.fileReader.apply(abbrPath);
        List<String> rawStart = FileReaderUtil.fileReader.apply(startTimePath);
        List<String> rawEnd = FileReaderUtil.fileReader.apply(endTimePath);

        List<AbbrNameTeamRecord> abbrNameTeamRecordList = DataParserUtil.abbrNameTeamParser.apply(rawAbbr);
        List<AbbrTimeRecord> abbrToStartTimePoints = DataParserUtil.abbrTimePointsParser.apply(rawStart);
        List<AbbrTimeRecord> abbrEndTimePoints = DataParserUtil.abbrTimePointsParser.apply(rawEnd);

        Map<String, List<LocalTime>> times =
                DataParserUtil.startEndTimeConcatenator.apply(abbrToStartTimePoints, abbrEndTimePoints);

        return RacerListBuilder.buildRacer.apply(abbrNameTeamRecordList, times);
    }

}
