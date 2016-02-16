package Threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer extends Thread {

	private MyArrayBlockingQueue<Integer> queue;
	private int toProduce;
	private static AtomicInteger count;
	private CyclicBarrier barrier;

	public Producer(MyArrayBlockingQueue<Integer> queue, CyclicBarrier barrier) {
		this.queue = queue;
		this.toProduce = queue.getCapacity();
		count = new AtomicInteger(-1);
		this.barrier = barrier;
	}

	public void run() {
		try {
			this.barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.produce();
		System.out.println("FINISHED PRODUCING.");
	}

	private void produce() {
		int produced;
		while ((produced = count.incrementAndGet()) < toProduce) {
			queue.offer(produced);
			System.out.println("Produced: " + produced);

		}
	}

}
