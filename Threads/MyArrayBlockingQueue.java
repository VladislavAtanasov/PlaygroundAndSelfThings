package Threads;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyArrayBlockingQueue<T> {

	private Queue<T> queue;

	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();
	private int capacity;

	public MyArrayBlockingQueue(int capacity) {
		this.capacity = capacity;
		queue = new LinkedList<>();
	}

	public T poll() {
		lock.lock();
		T removed = null;
		try {
			while (this.size() == 0) {
				notEmpty.await();
			}

			removed = queue.poll();
			notFull.signal();

		} catch (InterruptedException e) {
			e.printStackTrace();
			System.err.println("Interrupted poll.");
		} finally {
			lock.unlock();
		}
		return removed;
	}

	public T peek() {
		return queue.peek();
	}

	public synchronized int size() {
		return queue.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public boolean offer(T e) {
		lock.lock();
		Boolean added = false;
		try {
			while (this.size() == capacity) {
				notFull.await();
			}

			added = queue.offer(e);
			notEmpty.signal();
		} catch (InterruptedException e1) {
			System.err.println("Interrupted offer.");
			e1.printStackTrace();
		} finally {
			lock.unlock();
		}

		return added;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
