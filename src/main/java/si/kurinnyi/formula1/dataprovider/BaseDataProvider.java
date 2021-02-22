package si.kurinnyi.formula1.dataprovider;

import si.kurinnyi.formula1.racer.Racer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface BaseDataProvider {

    List<Racer> provideData() throws URISyntaxException, IOException;

}
