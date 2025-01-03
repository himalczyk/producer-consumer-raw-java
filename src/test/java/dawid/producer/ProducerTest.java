package dawid.producer;

import dawid.models.EventMesage;
import dawid.utils.JsonUtils;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;

import static org.mockito.Mockito.*;

public class ProducerTest {

    @Test
    public void testProducerAddsMessageToQueue() throws Exception {
        // Mock the BlockingQueue
        BlockingQueue<String> mockQueue = mock(BlockingQueue.class);

        // Create the Producer instance with the mocked queue
        Producer producer = new Producer(mockQueue);

        // Execute the produce logic directly (no thread needed)
        producer.produce();

        // Verify that the queue.put() method was called once
        verify(mockQueue, times(1)).put(any(String.class));

        // Verify the exact message content
        EventMesage expectedEvent = new EventMesage("USER_LOGIN", "2025-01-01T12:00:00Z", "{\"userId\":\"1234\"}");
        verify(mockQueue).put(JsonUtils.toJson(expectedEvent));
    }
}
