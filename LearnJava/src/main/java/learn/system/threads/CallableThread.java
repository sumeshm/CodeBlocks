package learn.system.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableThread implements Callable<CallableThread> {

	private String name;

	public CallableThread(String name)
	{
		this.name = name;
	}

	@Override
	public CallableThread call() throws Exception {
		Thread.sleep(3000);
		System.out.println("\t ***** Callable Thread is done *******");
		return this;
	}

	public Future<CallableThread> kickOff()
	{
		ExecutorService executor = Executors.newFixedThreadPool(2);
		return executor.submit(this);
	}

	public String getName() {
		return name;
	}
}
