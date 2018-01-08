package learn.sorting;

import static java.lang.System.out;
import static java.lang.System.err;
import static java.lang.System.in;


import java.util.Scanner;
import java.util.Vector;

import learn.common.IHandler;
import learn.common.Menu;

public class SortHandler implements IHandler {

	private Vector<Integer> dataToSort;
	private Scanner scanner;
	private Menu menu;
	
	public SortHandler()
	{
		readData();

		Vector<String> menuData = new Vector<String>();
		menuData.add("Selection");
		menuData.add("Insersion");
		menuData.add("Merge");
		menuData.add("Quick");
		menuData.add("Heap");

		menu = new Menu(menuData, "Sorting Menu", this);
	}
	
	@Override
	public Menu getMenu()
	{
		return menu;
	}
	
	@Override
	public IHandler handle(int index)
	{
		printData(dataToSort, "Before Sroting:");

		switch (index)
		{
		case 0:
		{
			out.println("\n SELCTION SORT:");
			SelectionSort selectionSort = new SelectionSort();
			selectionSort.sortAscendingOrder(dataToSort);
			printData(dataToSort, "After Selection Sroting:");
			break;
		}
		case 1:
		{
			out.println("\n INSERTION SORT:");
			InsertionSort insertionSort = new InsertionSort();
			insertionSort.sortAscendingOrder(dataToSort);
			printData(dataToSort, "After Insertion Sroting:");
			break;
		}
		case 2:
		{
			out.println("\n MEFRGE SORT:");
			MergeSort mergeSort = new MergeSort();
			printData(mergeSort.sortAscendingOrder(dataToSort), "After Merge Sroting:");
			break;
		}
		
		}
		
		return null;
	}
	
	protected void readData()
	{
		out.println(">>>>>>>>>>>> Lets create an array to sort, enter space seperated int(s):");
		out.println(">>>>>>>>>>>> e.g. 1 2 3 4  \n");


		scanner = new Scanner(in);
		String strInput = scanner.nextLine().trim();
		
		dataToSort = new Vector<Integer>();
		
		for (String value : strInput.split(" ")) {
			if (!value.trim().isEmpty())
			{
				try {
					dataToSort.add(new Integer(value));
				} catch (NumberFormatException e) {
					err.println("\n SKIPING INVALID ITEMS: " + value);
				}
			}
		}
	}
	
	protected void printData(Vector<Integer> dataToPrint, String msg)
	{
		out.print(msg + " - [");
		for(int current : dataToPrint)
		{
			out.print(current + " ");
		}
		out.print("]\n\n");
	}
}