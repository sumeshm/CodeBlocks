package learn.algo.sorting;

import java.util.Vector;

public class QuickSort {

	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - Choose an pivot index (e.g. middle element)");
		System.out.println("\t - move elements smaller than pivot to one side, greater than pivot to another");
		System.out.println("\t - recursively sort these two logical arrays and merge them back with pivot");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		sortInternal(data, 0, data.size() - 1);
	}

	protected void sortInternal(Vector<Integer> data, int startIndex, int endIndex)
	{
		if (startIndex >= endIndex)
		{
			return;
		}
		if ((endIndex - startIndex) == 1)
		{
			if (data.get(startIndex) > data.get(endIndex))
			{
				// swap
				int temp = data.get(startIndex);
				data.set(startIndex, data.get(endIndex));
				data.set(endIndex, temp);
			}

			return;
		}

		int pivotIndex = startIndex + (endIndex - startIndex) / 2;
		int pivotData = data.get(pivotIndex);
		int indexLeft = startIndex;
		int indexRight = endIndex;

		while (indexLeft < pivotIndex && indexRight > pivotIndex)
		{
			while (indexLeft < pivotIndex)
			{
				if (data.get(indexLeft) <= pivotData)
				{
					indexLeft++;
				}
				else
				{
					break;
				}
			}

			while (indexRight > pivotIndex)
			{
				if (pivotData <= data.get(indexRight))
				{
					indexRight--;
				}
				else
				{
					break;
				}
			}

			if (indexLeft < indexRight)
			{
				// swap
				int temp = data.get(indexLeft);
				data.set(indexLeft, data.get(indexRight));
				data.set(indexRight, temp);
			}

			indexLeft++;
			indexRight--;
		}
		
		sortInternal(data, startIndex, pivotIndex - 1);
		sortInternal(data, pivotIndex + 1, endIndex);
	}
}
