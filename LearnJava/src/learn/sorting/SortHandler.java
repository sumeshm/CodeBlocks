package learn.sorting;

import java.util.Vector;

import learn.common.IHandler;
import learn.common.Menu;

public class SortHandler implements IHandler {
	
	@Override
	public Menu getMenu()
	{
		Vector<String> menuData = new Vector<String>();
		menuData.add("Linear");
		menuData.add("Quick");
		menuData.add("Heap");
		menuData.add("Insertion");

		return new Menu(menuData, "Sorting Menu", this);
	}
	
	@Override
	public IHandler handle(int index)
	{
		IHandler handler = null;
		
		return handler;
	}

}
