package learn.menu;

import java.util.Stack;
import java.util.Vector;

import learn.btree.BTreeManager;
import learn.search.SearchManager;
import learn.sorting.SortManager;

public class MenuManager {

	private Stack<Menu> menuStack;

	public MenuManager()
	{
		menuStack = new Stack<Menu>();

		Vector<String> mainMenuData = new Vector<String>();
		mainMenuData.add("Sorting");
		mainMenuData.add("Search");
		mainMenuData.add("DS");
		mainMenuData.add("B-Tree");

		Menu mainMenu = new Menu(mainMenuData, "Main Menu");
		menuStack.push(mainMenu);
	}
	
	public void start()
	{
		while (!menuStack.isEmpty())
		{
			int retVal = menuStack.peek().print();
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
				switch (retVal)
				{
				case 0:
				{
					SortManager sortManager = new  SortManager();
					menuStack.push(sortManager.getMenu());
					break;
				}
				case 1:
				{
					SearchManager sortManager = new  SearchManager();
					menuStack.push(sortManager.getMenu());
					break;
				}
				case 3:
				{
					BTreeManager sortManager = new  BTreeManager();
					menuStack.push(sortManager.getMenu());
					break;
				}
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
