package dmp.daybyday;

import java.io.*;
import java.util.Vector;

public class FileOperator {
    private final File[] files;

    public FileOperator(File[] files) {
        this.files = files;
    }

    public byte[] combineContent() throws IOException {
        Vector<InputStream> streams = new Vector<>();
        for (File file : this.files) {
            FileInputStream fis = new FileInputStream(file);
            streams.addElement(fis);
        }

        try (InputStream is = new SequenceInputStream(streams.elements());
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            is.transferTo(out);
            return out.toByteArray();
        } finally {
            for (InputStream stream : streams) {
                stream.close();
            }
        }
    }
}
