package learn.common.handlers;

import java.util.Vector;

import learn.common.Menu;
import learn.system.threads.SimpleThread;

public class ThreadHandler implements IHandler {
	
	private Vector<String> menuData;
	private Menu menu;

	public ThreadHandler()
	{
		menuData = new Vector<String>();
		menuData.add("SimpleThread");
		menuData.add("SimpleRunnable");
		menuData.add("Two syncronized threads");

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
