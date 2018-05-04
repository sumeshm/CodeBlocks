package learn.sorting;

import java.util.Vector;

public class BubbleSort {

	public void printSteps()
	{
		System.out.println();
		System.out.println("\t - Bubble up the biggest value in the un-sorted data to higherst index, in each iteration");
		System.out.println("\t - For subsequent iteration, ignore the sorted section of the array (end of the data)");
		System.out.println("\t - Bubbling is achived by comparing adjacent items and swapping if necessary, then move to next index and repeat");
		System.out.println();
	}
	
	public void sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		int maxIndex = data.size() - 1;

		for (int indexUnsorted = 0; indexUnsorted < maxIndex; indexUnsorted++)
		{
			int lastIndex = maxIndex - indexUnsorted;
			for (int index = 0; index < lastIndex; index++)
			{
				int left = data.get(index);
				int right = data.get(index + 1);
				if (left > right)
				{
					data.set(index, right);
					data.set(index + 1, left);
				}
			}
		}
	}
}