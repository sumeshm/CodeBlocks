package learn.sorting;

import java.util.Vector;

public class HeapSort {

	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - Treat data array as a minimum heap");
		System.out.println("\t - Start heapify from last element");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();

		int lastIndex = data.size() - 1;
		while (lastIndex > 1)
		{
			// get max item to root
			maxHeapify(data, lastIndex);

			// move max to end by swapping
			int temp = data.get(0);
			data.set(0, data.get(lastIndex));
			data.set(lastIndex, temp);
			
			lastIndex--;
		}
	}

	protected void maxHeapify(Vector<Integer> data, int endIndex)
	{
		int current = endIndex;
		
		while (current >= 1)
		{
			int parent = getParentIndex(current);

			if (parent >= 0 && data.get(current) > data.get(parent))
			{
				int temp = data.get(current);
				data.set(current, data.get(parent));
				data.set(parent, temp);
			}
			
			current--;
		}
	}

	int getParentIndex(int index)
	{
		if (index == 2 || index == 1)
		{
			return 0;
		}
		
		if (index % 2 == 0)
		{
			return (index - 2) / 2;
		}
		else
		{
			return (index - 1) / 2;
		}
	}
}
