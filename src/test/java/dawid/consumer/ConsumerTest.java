package dawid.consumer;

import dawid.models.EventMesage;
import dawid.utils.JsonUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class ConsumerTest {

    @Test
    public void testConsumerProcessesMessage() throws Exception {
        // Create a shared queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        // Create a test EventMesage object and serialize it to JSON
        EventMesage testEvent = new EventMesage("USER_LOGIN", "2025-01-01T12:00:00Z", "{\"userId\":\"1234\"}");
        String jsonMessage = JsonUtils.toJson(testEvent);

        // Add the serialized JSON to the queue
        queue.put(jsonMessage);

        // Simulate Consumer behavior
        Consumer consumer = new Consumer(queue);
        Thread consumerThread = new Thread(consumer);

        // Redirect System.out to capture output for verification
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Start the Consumer thread
        consumerThread.start();

        // Allow the consumer to process the message (wait a bit for the thread)
        Thread.sleep(2000);

        // Stop the thread
        consumerThread.interrupt();
        consumerThread.join();

        // Restore the original System.out
        System.setOut(originalOut);

        // Get the output captured from System.out
        String output = outputStream.toString();

        // Verify the output contains the correct deserialized EventMesage
        assertTrue(output.contains("USER_LOGIN"));
        assertTrue(output.contains("2025-01-01T12:00:00Z"));
        assertTrue(output.contains("{\"userId\":\"1234\"}"));
    }
}
