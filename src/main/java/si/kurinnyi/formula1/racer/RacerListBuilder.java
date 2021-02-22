package si.kurinnyi.formula1.racer;

import si.kurinnyi.formula1.dataparser.DataParserUtil;
import si.kurinnyi.formula1.dataprocessor.AbbrNameTeamRecord;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class RacerListBuilder {

    public static final BiFunction<List<AbbrNameTeamRecord>, Map<String, List<LocalTime>>, List<Racer>>
            buildRacer = (list, times)  -> list.stream()
            .collect(Collectors.toMap(AbbrNameTeamRecord::getAbbr, element -> element))
            .entrySet()
            .stream()
            .map(element -> new Racer(element.getValue().getName(), element.getValue().getTeam(),
                    DataParserUtil.lapListBuilder.apply(times.get(element.getKey()))))
            .collect(Collectors.toList());

}
