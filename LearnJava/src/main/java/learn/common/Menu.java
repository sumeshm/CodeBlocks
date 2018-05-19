package learn.common;

import java.util.Scanner;
import java.util.Vector;

import learn.common.handlers.IHandler;

public class Menu {

	private Vector<String> data;
	private String title;
	private Scanner scanner;
	
	public Menu(Vector<String> data, String title, IHandler handler)
	{
		this.data = data;
		this.title = title;
	}

	public int print() {
		int retVal = MenuStatus.RET_VAL_INVLID.getLevelCode();
		while (true) {
			printOptions();
			retVal = readInput();
			if (retVal != MenuStatus.RET_VAL_INVLID.getLevelCode())
			{
				break;
			}
		}

		return retVal;
	}

	protected void printOptions() {
		System.out.println();
		System.out.println("-----------------------------------------------------------");
		System.out.println(" " + title + " : choose an index");
		System.out.println("-----------------------------------------------------------");
		int index = 0;
		for (String option : data) {
			System.out.println(" " + index + ". " + option);
			index++;
		}
		System.out.println();
		System.out.println(" b. BACK");
		System.out.println(" q. QUIT");
		System.out.println("-----------------------------------------------------------");
		System.out.println();
	}

	protected int readInput() {
		int retVal = MenuStatus.RET_VAL_INVLID.getLevelCode();

		System.out.print("Choose now : ");

		scanner = new Scanner(System.in);
		String strInput = scanner.nextLine();

		if ("q".equals(strInput)) {
			return MenuStatus.RET_VAL_QUIT.getLevelCode();
		}
		if ("b".equals(strInput)) {
			return MenuStatus.RET_VAL_BACK.getLevelCode();
		}

		try {
			retVal = Integer.parseInt(strInput);
			if (retVal < 0 || retVal >= data.size())
			{
				System.err.println("\n INVALID INPUT: try again \n");
				retVal = MenuStatus.RET_VAL_INVLID.getLevelCode();
			}
		} catch (NumberFormatException e) {
			System.err.println("\n INVALID INPUT: try again \n");
		}

		return retVal;
	}
}
