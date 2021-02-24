package si.kurinnyi.formula1.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderUtil {

    public static final Function<Path, List<String>> fileReader = path -> {
        if (path == null) {
            throw new NullPointerException("File path is null.");
        }
        try {
            Stream<String> streamFromFiles = Files.lines(path);
            if (Files.lines(path).findAny().isEmpty()) {
                throw new IllegalStateException("Error reading file");
            }
            return streamFromFiles.collect(Collectors.toList());
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new ArrayList<>();
    };

}
