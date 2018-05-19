package learn.algo.sorting;

import java.util.Vector;

public class InsertionSort {

	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - View the data as combination of sorted and unsorted");
		System.out.println("\t - Pick first item of un-sorted part and insert it to appropriate position in sorted part");
		System.out.println("\t - This can be done in place with swapping new item from right to left (backwards) of sorted part, one place at a time");
		System.out.println("\t - Repeat this untill the new eleement reaches appropriate position in srorted part");
		System.out.println("\t - The sorted list will start with one element (the first one) and grow");
		System.out.println("\t - The un-sorted list will start shriking from front towards back");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		
		for (int uIndex = 1; uIndex < data.size(); uIndex++)
		{
			// scan unsorted
			for (int sIndex = 0; sIndex < uIndex; sIndex++)
			{
				// scan sorted
				if (data.get(sIndex) > data.get(uIndex))
				{
					// compare and swap
					int temp = data.get(sIndex);
					data.set(sIndex, data.get(uIndex));
					data.set(uIndex, temp);
				}
			}
		}
	}	
}