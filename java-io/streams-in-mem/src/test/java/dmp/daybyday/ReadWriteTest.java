package dmp.daybyday;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadWriteTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture the output
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        // Restore original System.out
        System.setOut(originalOut);
    }

    @Test
    public void testProcess() throws IOException, InterruptedException {
        String[] content = {
                "Hello, world!",
                "This is a test string.",
                "Another line of text."
        };
        ReadWrite readWrite = new ReadWrite(content);
        readWrite.process();

        // Wait for the executor to finish processing
        Thread.sleep(1000);

        // Get the expected output
        StringBuilder expectedOutput = new StringBuilder();
        for (String line : content) {
            byte[] expectedBytes = (line + System.lineSeparator()).getBytes(StandardCharsets.UTF_8);
            for (byte b : expectedBytes) {
                expectedOutput.append(b);
            }
        }

        // Compare the captured output with the expected output
        assertEquals(expectedOutput.toString(), outContent.toString());
    }
}
