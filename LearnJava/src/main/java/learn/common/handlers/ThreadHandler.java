package learn.common.handlers;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

import learn.common.Menu;
import learn.system.threads.PrintNumSequence;
import learn.system.threads.SimpleThread;

public class ThreadHandler implements IHandler {
	
	private Vector<String> menuData;
	private Menu menu;

	public ThreadHandler()
	{
		menuData = new Vector<String>();
		menuData.add("SimpleThread");
		menuData.add("SimpleRunnable");
		menuData.add("Three threads print in sequence");

		menu = new Menu(menuData, "Threads Menu", this);
	}

	@Override
	public Menu getMenu()
	{
		return menu;
	}
	
	@Override
	public IHandler handle(int index)
	{
		switch (index)
		{
		case 0:
		{
			SimpleThread simpleThread = new SimpleThread();
			simpleThread.start();
			break;
		}
		case 1:
		{
			Runnable runnable = () -> { 
				int count = 0;
				while (count < 100)
				{
					System.out.println(" --- Simple Runnable --- count = " + count++);
				}
			};
			Thread simpleRunnable = new Thread(runnable);
			simpleRunnable.start();
			break;
		}
		case 2:
		{
	          AtomicInteger atomicInteger = new AtomicInteger(0);
	          new PrintNumSequence(atomicInteger, 0, 10).start();	// even
	          new PrintNumSequence(atomicInteger, 1, 10).start();	// odd
	          break;
		}
		case 3:
		{
			break;
		}
		}
		
		return null;
	}
}
