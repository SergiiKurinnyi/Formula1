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
        try {
            Stream<String> streamFromFiles = Files.lines(path);
            return streamFromFiles.collect(Collectors.toList());
        } catch (IOException io) {
            System.out.println("File not found.");
        }

        return new ArrayList<>();
    };

}
