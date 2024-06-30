package dmp.daybyday;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParallelReader implements Readable {

    @Override
    public List<String[]> read(String path) throws IOException {
        List<String[]> list = Collections.synchronizedList(new ArrayList<>());
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {

            br.lines().parallel().forEach(line -> {
                // Process each line in parallel
                String[] values = line.split(COMMA_REGEX);
                // Do something with values
                list.add(values);
            });
            return list;
        }
    }
}
