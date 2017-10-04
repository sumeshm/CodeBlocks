package learn.sorting;

import java.util.Vector;

import learn.menu.Menu;

public class SortManager {
	
	public Menu getMenu()
	{
		Vector<String> menuData = new Vector<String>();
		menuData.add("Linear");
		menuData.add("Quick");
		menuData.add("Heap");
		menuData.add("Insertion");

		return new Menu(menuData, "Sorting Menu");
	}
	
	public void handle(int index)
	{
		
	}

}
