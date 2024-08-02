package dmp.daybyday;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class StringConverter {
    private final String src;

    public StringConverter(String src) {
        this.src = src;
    }

    public byte[] convert() {
        // read data to stream
        ByteArrayInputStream is = new ByteArrayInputStream(this.src.getBytes());

        // write byte to stream
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        int byteValue;
        while ((byteValue = is.read()) != -1) {
            os.write(byteValue);
        }

        // return
        return os.toByteArray();
    }
}
