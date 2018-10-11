package learn.threads.algo.sequence;

import java.util.concurrent.atomic.AtomicBoolean;

public class PrinterThread extends Thread {

	private Object printLock;
	private int count;
	private AtomicBoolean isDone = new AtomicBoolean(false);
	private String prefix;

	public PrinterThread(Object lock, Boolean isEven) {
		this.printLock = lock;

		if (isEven) {
			count = 0;
			prefix = "EVEN: ";
		} else {
			count = 1;
			prefix = "ODD:  ";

		}
	}

	@Override
	public void run() {
		while (!isDone.get() && count < 100) {
			synchronized (printLock) {
				try {
					System.out.println(prefix + count);
					count += 2;
					Thread.sleep(2000);

					printLock.notifyAll();
					printLock.wait();
				} catch (InterruptedException e) {
					//e.printStackTrace();
					System.err.println(prefix + "WARNING: exit due to interruption..");
				}
			}
		}
	}

	@Override
	public void interrupt() {
		isDone.set(Boolean.TRUE);
		super.interrupt();
	}
}
