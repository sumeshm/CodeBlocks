package learn.sorting;

import java.util.Vector;

public class InsertionSort {
	
	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - View the data as combination of sorted and unsorted");
		System.out.println("\t - Pick first item of un-sorted part and insert it to appropriate position in sorted part");
		System.out.println("\t - This can be done in place with swapping new item from right to left of sorted part, one place at a time");
		System.out.println("\t - Repeat this untill the new eleement reaches appropriate position in srorted part");
		System.out.println("\t - The sorted list will start with one element (the first one) and grow");
		System.out.println("\t - The un-sorted list will start shriking from front towards back");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		
		for (int index = 1; index < data.size(); index++)
		{
			moveToSorted(data, index);
		}		
	}
	
	public void moveToSorted(Vector<Integer> data, int indexToMove)
	{
		for (int i = indexToMove; i > 0; i--)
		{
			if (data.get(indexToMove) < data.get(indexToMove - 1))
			{
				swapItems(data, indexToMove - 1, indexToMove);
			}
		}
	}

	protected boolean swapItems(Vector<Integer> data, int indexA, int indexB)
	{
		if (indexA < 0 || indexA >= data.size() || indexB < 0 || indexB >= data.size() || indexA == indexB)
		{
			return false;
		}
		
		int temp = data.get(indexA);
		data.set(indexA, data.get(indexB));
		data.set(indexB, temp);
		
		return true;
	}

	protected int getMinimimIndex(Vector<Integer> data, int startIndex)
	{
		int retVal = startIndex;
		for (int index = startIndex; index < data.size(); index++)
		{
			if (data.get(index) < data.get(retVal))
			{
				retVal = index;
			}
		}
		
		return retVal;
	}
	
}