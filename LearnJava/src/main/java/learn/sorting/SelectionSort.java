package learn.sorting;

import java.util.Vector;

public class SelectionSort {
	
	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - View the data as combination of sorted and unsorted");
		System.out.println("\t - Find the minimum from unsorted and append it to end of sorted");
		System.out.println("\t - This can be done in place with find-minimum and swap");
		System.out.println("\t - The sorted list will start with zero elements and grow from the start");
		System.out.println("\t - The un-sorted list will start shriking from front towards back");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		
		for (int index = 0; index < data.size(); index++)
		{
			int minIndex = getMinimimIndex(data, index);
			swapItems(data, minIndex, index);
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