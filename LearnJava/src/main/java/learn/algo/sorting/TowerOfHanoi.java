package learn.algo.sorting;

import java.util.Stack;
import java.util.Vector;

public class TowerOfHanoi {

	private Stack<String> towerA;
	private Stack<String> towerB;
	private Stack<String> towerC;

	public TowerOfHanoi(Vector<String> dataList)
	{
		towerA = new Stack<>();
		towerB = new Stack<>();
		towerC = new Stack<>();

		for (String data : dataList)
		{
			towerA.add(data);
		}

		printItems();
		
		transfer(towerA, towerC, towerB, towerA.size());
	}
	
	private void transfer(Stack<String> srcTower, Stack<String> destTower, Stack<String> bkpTower, int count)
	{
		if (count == 1)
		{
			destTower.push(srcTower.pop());
			return;
		}

		if (!srcTower.isEmpty()) {
			transfer(srcTower, bkpTower, destTower, count - 1);
			printItems();
			transfer(srcTower, destTower, bkpTower, 1);
			printItems();
			transfer(bkpTower, destTower, srcTower, count - 1);
			printItems();
		}
	}

	private void printItems()
	{
		System.out.println();
		printTower(towerA, "\t Tower_A");
		printTower(towerB, "\t\t Tower_B");
		printTower(towerC, "\t\t Tower_C");
	}

	private void printTower(Stack<String> tower, String msg)
	{
		System.out.print(msg + ": [ ");
		for (String data : tower)
		{
			System.out.print(data + " / ");
		}
		System.out.print(" ]");
	}
}
