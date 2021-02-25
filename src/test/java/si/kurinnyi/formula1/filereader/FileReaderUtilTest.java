package si.kurinnyi.formula1.filereader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtilTest {

    @Test
    void fileReaderShouldThrowIOExceptionIfPathIsNull() {
        String expected = "File path is null.";
        Exception exception = assertThrows(NullPointerException.class, () -> FileReaderUtil.fileReader.apply(null));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void fileReaderShouldThrowExceptionIfFileIsEmpty() throws URISyntaxException {
        String expected = "Error reading file";
        Path testPath = Paths.get(getClass().getClassLoader().getResource("empty.log").toURI());
        Exception exception = assertThrows(IllegalStateException.class, () -> FileReaderUtil.fileReader.apply(testPath));
        assertEquals(expected, exception.getMessage());
    }

    @Test
    void fileReaderShouldReturnStringListWhenFileHasStrings() throws URISyntaxException {
        List<String> expected = new ArrayList<>();
        expected.add("SVF2018-05-24_12:02:58.917");
        expected.add("NHR2018-05-24_12:02:49.914");
        Path testPath = Paths.get(getClass().getClassLoader().getResource("small_start.log").toURI());
        List<String> actual = FileReaderUtil.fileReader.apply(testPath);
        assertEquals(expected, actual);
    }

}
