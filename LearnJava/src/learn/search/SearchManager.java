package learn.search;

import java.util.Vector;

import learn.menu.Menu;

public class SearchManager {

	public Menu getMenu() {
		Vector<String> menuData = new Vector<String>();
		menuData.add("Binary");
		menuData.add("BinarySearchTree");

		return new Menu(menuData, "Search Menu");
	}

}
