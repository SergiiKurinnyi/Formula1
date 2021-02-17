package si.kurinnyi.formula1.tabledescriptor;

import si.kurinnyi.formula1.racer.Racer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ColumnFactory implements Column {


    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getData(Racer racer) {
        return null;
    }

    @Override
    public Comparator<Racer> getComparator() {
        return null;
    }

/*    Column getColumn(ColumnType type) {

    return null;
    }*/

    public List<Column> getColumn(List<Racer> racerList, TableType tableType) {

        switch (tableType) {
            case RACER_NAME:
                getNames(racerList);
                break;
            case LAP_COUNT:
                getLapsCount(racerList);
                break;

        }


    return new ArrayList<Column>();

    }

    private List<String> getNames(List<Racer> racerList) {

         racerList.stream()
                .map(x -> x.getName())
                .collect(Collectors.toList()).forEach(System.out::println);
        return null;
    }

   private List<String> getLapsCount(List<Racer> racerList) {

         racerList.stream()
                 .filter(x -> x.getLaps() != null)
                 .map(x -> x.getName() + " " + x.getTeam() + " " + x.getLaps().size() + " laps")
                 .collect(Collectors.toList()).forEach(System.out::println);
         return null;
    }

}
