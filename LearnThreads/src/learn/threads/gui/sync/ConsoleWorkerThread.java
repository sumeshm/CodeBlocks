package learn.threads.gui.sync;

public class ConsoleWorkerThread extends Thread {

	private SynchronizedConsole console;
	private int sourceId;

	public ConsoleWorkerThread(int sourceId, SynchronizedConsole console) {
		this.sourceId = sourceId;
		this.console = console;
	}

	@Override
	public void run() {
		int count = 0;
		while (count < 20) {
			console.write(sourceId, "[" + count + "] line-1");
			console.write(sourceId, "[" + count + "] line-2");
			console.write(sourceId, "[" + count + "] line-3");
			console.write(sourceId, "[" + count + "] line-4");
			console.write(sourceId, "[" + count + "] line-5");
			count++;
		}
	}
}
