package dmp.daybyday;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getAnonymousLogger();

    private static final String FILE_NAME = "";

    public static void main(String[] args) throws IOException {
        List<String[]> strings = new ParallelReader().startRead(FILE_NAME);

        LOGGER.info("Size: " + strings.size());
    }
}