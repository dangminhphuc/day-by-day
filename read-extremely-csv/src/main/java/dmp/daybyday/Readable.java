package dmp.daybyday;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@FunctionalInterface
public interface Readable {
    Logger LOGGER = Logger.getAnonymousLogger();
    String COMMA_REGEX = ",";

    default List<String[]> startRead(String path) throws IOException {
        long s = System.currentTimeMillis();
        try {
            return read(path);
        } finally {
            LOGGER.info("" + (System.currentTimeMillis() - s));
        }
    }

    List<String[]> read(String path) throws IOException;
}
