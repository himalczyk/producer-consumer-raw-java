package dawid.producer;

import java.util.concurrent.BlockingQueue;

import dawid.models.EventMesage;
import dawid.utils.JsonUtils;

public class Producer implements Runnable {

    private final BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Running Producer");
        try {
            while (true) {
                produce(); // Delegate to produce method
                Thread.sleep(1000); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }
    }

    public void produce() throws InterruptedException {
        EventMesage event = new EventMesage("USER_LOGIN", "2025-01-01T12:00:00Z", "{\"userId\":\"1234\"}");
        String jsonEvent = JsonUtils.toJson(event);
        System.out.println("Producing: " + jsonEvent);
        queue.put(jsonEvent); // Add to the queue
    }
}
