package si.kurinnyi.formula1.filereader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileReader {

    List<String> readFile(Path path) throws IOException;

}
