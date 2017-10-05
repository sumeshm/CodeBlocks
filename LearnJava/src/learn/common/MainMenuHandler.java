package learn.common;

import java.util.Vector;

import learn.common.IHandler;
import learn.common.Menu;
import learn.search.SearchHandler;
import learn.sorting.SortHandler;
import learn.tree.TreeHandler;

public class MainMenuHandler implements IHandler {

	private Menu menu;
	
	public MainMenuHandler()
	{
		Vector<String> menuData = new Vector<String>();
		menuData.add("Sorting");
		menuData.add("Search");
		menuData.add("DS");
		menuData.add("B-Tree");

		this.menu = new Menu(menuData, "Main Menu", this);
	}

	@Override
	public Menu getMenu() {
		return menu;
	}

	@Override
	public IHandler handle(int index) {
		IHandler handler = null;
		switch (index)
		{
		case 0:
		{
			handler = new SortHandler();
			break;
		}
		case 1:
		{
			handler = new SearchHandler();
			break;
		}
		case 3:
		{
			handler = new TreeHandler();
			break;
		}
		}
		
		return handler;
	}

}
