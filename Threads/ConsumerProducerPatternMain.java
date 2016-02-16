package Threads;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConsumerProducerPatternMain {

	public static void main(String[] args) throws InterruptedException {
		MyArrayBlockingQueue<Integer> queue = new MyArrayBlockingQueue<>(300);

		int numberOfProducers = 4;
		int numberOfConsumers = 6;
		CyclicBarrier barrier = new CyclicBarrier(numberOfConsumers + numberOfProducers);
		Thread[] arr = new Thread[numberOfConsumers + numberOfProducers];
		for (int i = 0; i < numberOfProducers; i++) {
			Producer pr = new Producer(queue, barrier);
			arr[i] = pr;
		}
		for (int i = 0; i < numberOfConsumers; i++) {
			Consumer consumer = new Consumer(queue, barrier);
			arr[numberOfProducers + i] = consumer;
		}

		ExecutorService executor = Executors.newFixedThreadPool(arr.length);
		double start = System.nanoTime() / 100_000_000.0;

		for (int i = 0; i < arr.length; i++) {
			executor.execute(arr[i]);
		}

		executor.shutdown();
		executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

		System.out.println((System.nanoTime() / 100_000_000.0 - start) + " seconds.");
	}

}
