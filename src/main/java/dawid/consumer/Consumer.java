package dawid.consumer;

import java.util.concurrent.BlockingQueue;
import dawid.models.EventMesage;
import dawid.utils.JsonUtils; 

public class Consumer implements Runnable {
    
    private final BlockingQueue<String> queue;
    
    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("Running Consumer");
        try {
            while (true) {
                String eventJson = queue.take(); // Puts an element in the queue, blocks if full
                System.out.println("Consumed event:" + JsonUtils.fromJson(eventJson, EventMesage.class));
                Thread.sleep(1000); // Simulate production time
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Producer interrupted");
        }
        
    }
}
