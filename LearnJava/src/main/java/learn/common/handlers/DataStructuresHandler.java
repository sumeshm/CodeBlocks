package learn.common.handlers;

import java.util.Vector;

import learn.algo.sorting.TowerOfHanoi;
import learn.common.Menu;

public class DataStructuresHandler implements IHandler {

	private Menu menu;
	
	public DataStructuresHandler()
	{
		Vector<String> menuData = new Vector<String>();
		menuData.add("B-Tree");
		menuData.add("Linked List");
		menuData.add("Tower of Hanoi");
		menuData.add("Queue with Stack");

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
			handler = new TreeHandler();
			break;
		}
		case 1:
		{
			handler = new ListHandler();
			break;
		}
		case 2:
		{
			Vector<String> dataList = new Vector<>();
			dataList.add("A");
			dataList.add("B");
			dataList.add("C");
			dataList.add("D");
			new TowerOfHanoi(dataList);
			break;
		}
		}
		
		return handler;
	}
}
