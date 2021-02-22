package si.kurinnyi.formula1.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    public List<String> readFile(Path path) throws IOException {

        Stream<String> streamFromFiles = Files.lines(path);
        return streamFromFiles.collect(Collectors.toList());
    }

}
