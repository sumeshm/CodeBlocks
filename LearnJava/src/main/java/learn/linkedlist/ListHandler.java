package learn.linkedlist;

import static java.lang.System.out;

import java.util.Scanner;
import java.util.Vector;

import learn.common.IHandler;
import learn.common.Menu;

public class ListHandler implements IHandler {
	
	private Vector<String> menuData;
	private Scanner scanner;
	private Menu menu;
	private LinkedList<Integer> list;
	private DoublyLinkedList<Integer> dList;

	public ListHandler()
	{
		menuData = new Vector<String>();
		menuData.add("Linked List: Print");
		menuData.add("Linked List: Sort");
		menuData.add("Linked List: Reverse list");
		menuData.add("D-Linked List: Print");
		menuData.add("D-Linked List: Sort");
		menuData.add("D-Linked List: Reverse list");

		menu = new Menu(menuData, "List Menu", this);
	}

	@Override
	public Menu getMenu()
	{
		return menu;
	}
	
	@Override
	public IHandler handle(int index)
	{
		if (createLinkedList() <= 0)
		{
			out.println(">>>>>>>>>>>> Insufficient items to sort, try again... \n");
			return null;
		}

		switch (index)
		{
		case 0:
		{
			list.printList("");
			break;
		}
		case 1:
		{
			list.sort();
			break;
		}
		case 2:
		{
			list.reverseList();
			break;
		}
		case 3:
		{
			dList.printList("");
			break;
		}
		case 4:
		{
			dList.sort();
			break;
		}
		case 5:
		{
			dList.reverseList();
			break;
		}
		}
		
		return null;
	}
	
	protected int createLinkedList()
	{
		System.out.println(">>>>>>>>>>>> Lets create the tree, enter an arrya of space seperated int(s):");
		System.out.println(">>>>>>>>>>>> e.g. 1 2 3 4  \n");

		scanner = new Scanner(System.in);
		String strInput = scanner.nextLine().trim();
		Vector<Integer> inputData = new Vector<>();
		
		for (String value : strInput.split(" ")) {
			if (!value.trim().isEmpty())
			{
				try {
					inputData.add(new Integer(value));
				} catch (NumberFormatException e) {
					//err.println("\n SKIPING INVALID ITEMS: " + value);
				}
			}
		}

		list = new LinkedList<>(inputData);
		dList = new DoublyLinkedList<>(inputData);
		return inputData.size();
	}
}
