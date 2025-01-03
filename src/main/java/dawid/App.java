package dawid;

import dawid.producer.Producer;
import dawid.consumer.Consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class App {
    public static void main(String[] args) throws Exception{
        // Create a shared queue
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);

        // Create and start producer and consumer threads
        Thread producerThread = new Thread(new Producer(queue));
        Thread consumerThread = new Thread(new Consumer(queue));

        producerThread.start();
        consumerThread.start();
    }
}
