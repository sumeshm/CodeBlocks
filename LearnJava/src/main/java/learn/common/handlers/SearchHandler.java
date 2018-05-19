package learn.common.handlers;

import java.util.Vector;

import learn.common.Menu;

public class SearchHandler implements IHandler {

	@Override
	public Menu getMenu() {
		Vector<String> menuData = new Vector<String>();
		menuData.add("Binary");
		menuData.add("BinarySearchTree");

		return new Menu(menuData, "Search Menu", this);
	}

	@Override
	public IHandler handle(int index) {
		return null;
	}

}
