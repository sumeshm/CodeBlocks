package learn.sorting;

import java.util.Vector;

public class SelectionSort {

	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - View the data as combination of sorted and unsorted");
		System.out.println("\t - Select smallest element of unsorted and append it to end of sorted");
		System.out.println("\t - This can be done in place with find-minimum and swap");
		System.out.println("\t - The sorted list will start with zero elements and grow from the start");
		System.out.println("\t - The un-sorted list will start shriking from front towards back");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();

		int uStart = 0;
		while (uStart < data.size())
		{
			// find minimum element of unsorted
			int minIndex = getMinimimIndex(data, uStart);
			
			// move minimum item to start of unsorted
			int temp = data.get(minIndex);
			data.set(minIndex, data.get(uStart));
			data.set(uStart, temp);

			// move the staring point of unsorted
			uStart++;
		}
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