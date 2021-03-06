package learn.common;

import java.util.Stack;

import learn.common.handlers.IHandler;
import learn.common.handlers.MainMenuHandler;

public class MenuManager {

	interface Addable{  
	    int add(int a,int b);  
	}

	private Stack<IHandler> menuStack;

	public MenuManager()
	{
		menuStack = new Stack<IHandler>();
		
		MainMenuHandler handler = new MainMenuHandler();
		menuStack.push(handler);
	}
	
	public void start()
	{
		while (!menuStack.isEmpty())
		{
			int retVal = menuStack.peek().getMenu().print();
			if (retVal == MenuStatus.RET_VAL_QUIT.getLevelCode())
			{
				menuStack.clear();
			}
			else if (retVal == MenuStatus.RET_VAL_BACK.getLevelCode())
			{
				menuStack.pop();
			}
			else
			{
				IHandler handler = menuStack.peek().handle(retVal);
				if (handler != null)
				{
					menuStack.push(handler);
				}
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Well well....here we go again...");
		MenuManager manager = new MenuManager();
		manager.start();
	}

}
