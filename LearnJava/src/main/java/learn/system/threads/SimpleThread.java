package learn.system.threads;

public class SimpleThread extends Thread {

	private static int count = 0;
	@Override
	public void run() {
		count = 0;
		while (count < 100)
		{
			System.out.println(" --- Simple Thread --- count = " + count++);
		}
	}

}
