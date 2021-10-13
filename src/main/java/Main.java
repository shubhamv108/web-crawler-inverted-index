import utils.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        List<String> seedUrls = FileUtils.readAllLines(Path.of(args[0]));

    }

}
