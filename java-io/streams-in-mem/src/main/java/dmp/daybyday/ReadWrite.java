package dmp.daybyday;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWrite {
    private final ExecutorService executor = Executors.newFixedThreadPool(2);

    private final String[] src;

    public ReadWrite(String[] src) {
        this.src = src;
    }

    public void process() throws IOException {

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);

        executor.submit(() -> {
            for (String s : this.src) {
                try {
                    pos.write(s.getBytes(StandardCharsets.UTF_8));
                    pos.write("\n".getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executor.submit(() -> {
            try {
                int data;
                while ((data = pis.read()) != -1) {
                    System.out.print(data);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        executor.shutdown();
    }
}
