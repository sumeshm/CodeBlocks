package learn.system.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumSequence extends Thread {

	private AtomicInteger count;
	private int id;
	private final int MAX;

	public PrintNumSequence(AtomicInteger atomicInteger, int id, int max) {
		this.count = atomicInteger;
		this.id = id;
		this.MAX = max;
	}

	@Override
	public void run() {
		do {
			synchronized (count) {
				int currentCount = count.intValue();
				if (currentCount >= MAX) {
					count.notifyAll();
					break;
				}

				// match thread id and print (even/odd)
				if ((currentCount % 2) == id) {
					System.out.println("Thread [" + id + "] says --" + currentCount);
					currentCount = count.incrementAndGet();
				}

				// allow other thread to run
				count.notifyAll();

				// wait for other threads to print
				try {
					count.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} while (true);
	}
}
