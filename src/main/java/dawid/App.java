package dawid;

import dawid.producer.Producer;
import dawid.consumer.Consumer;
import dawid.models.EventMesage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
    public static void main(String[] args) throws Exception{
        // Create a shared queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        ObjectMapper mapper = new ObjectMapper();

        // Create an Event object
        EventMesage event = new EventMesage("USER_LOGIN", "2025-01-01T12:00:00Z", "{\"userId\":\"1234\"}");

        // Serialize to JSON
        String json = mapper.writeValueAsString(event);
        System.out.println("Serialized JSON: " + json);

        // Deserialize from JSON
        EventMesage deserializedEvent = mapper.readValue(json, EventMesage.class);
        System.out.println("Deserialized Event: " + deserializedEvent);

        // Create and start producer and consumer threads
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
