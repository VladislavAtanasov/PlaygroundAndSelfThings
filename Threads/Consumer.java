package Threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

public class Consumer extends Thread {

	private MyArrayBlockingQueue<Integer> queue;
	private int toProduce;
	private static AtomicInteger toConsume;
	private CyclicBarrier barrier;

	public Consumer(MyArrayBlockingQueue<Integer> queue, CyclicBarrier barrier) {
		this.queue = queue;
		this.toProduce = queue.getCapacity();
		this.barrier = barrier;
		toConsume = new AtomicInteger(toProduce);
	}

	public void run() {
		try {
			this.barrier.await();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.consume();
		System.out.println("FINISHED CONSUMMING.");
	}

	private void consume() {
		while (toConsume.getAndDecrement() > 0) {
			System.out.println("Consumed: " + queue.poll());
		}
	}
}
