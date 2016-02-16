package Threads;

import java.util.LinkedList;
import java.util.Queue;

public class ConsumerProducerPattern {

	private static Queue<String> queue = new LinkedList<>();
	private static Boolean empty = true;
	private static String[] messages = new String[] { "drugo", "fmi", "thebest", "drugo", "gmail" };

	static class Producer extends Thread {
		private Message message;

		@Override
		public void run() {

			for (String mes : messages) {
				message.produceMessage(mes);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("FINISHED PRODUCTING.");
		}

		public void setMessage(Message mes) {
			this.message = mes;
		}
	}

	static class Consumer extends Thread {
		private Message mes;

		@Override
		public void run() {
			for (int i = 0; i < messages.length; i++) {
				mes.receive();
			}

			System.out.println("FINISHED CONSUMMING.");
		}

		public void setMes(Message mes) {
			this.mes = mes;
		}

	}

	static class Message {

		private synchronized void produceMessage(String mes) {

			while (!empty) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			queue.add(mes);
			System.out.println("Produce");
			System.out.println(mes);
			System.out.println("Queue Size: " + queue.size());
			System.out.println();
			empty = false;
			notifyAll();
		}

		private synchronized void receive() {

			while (empty) {
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String received = queue.poll();
			empty = true;
			System.out.println("Received");
			System.out.println(received);
			System.out.println("Queue Size: " + queue.size());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Consumer consumer = new Consumer();
		Producer producer = new Producer();
		Message message = new Message();
		producer.setMessage(message);
		consumer.setMes(message);

		producer.start();
		consumer.start();

	}

}
