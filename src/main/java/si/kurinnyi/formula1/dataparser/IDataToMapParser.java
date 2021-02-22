package si.kurinnyi.formula1.dataparser;

import si.kurinnyi.formula1.dataformatter.AbbrTimePoints;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface IDataToMapParser {

    Map<String, String> parseAbbreviationToName(List<String> abbreviations);

    Map<String, String> parseAbbreviationToTeam(List<String> abbreviations);

    Map<String, List<AbbrTimePoints>> abbrToTimePoints(List<String> rawTime);

    Map<String, List<Duration>> abbrToLaps(
            Map<String, List<AbbrTimePoints>> abbrToStart, Map<String, List<AbbrTimePoints>> abbrToEnd);

}
