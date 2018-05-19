package learn.algo.sorting;

import java.util.Vector;

public class MergeSort {
	
	public void printSteps()
	{
		System.out.println();
		System.out.println();
	}
	
	public Vector<Integer> sortAscendingOrder(Vector<Integer> data)
	{
		printSteps();
		return sortInternal(data, 0, data.size() - 1);
	}
	
	protected Vector<Integer> sortInternal(Vector<Integer> data, int startIndex, int endIndex)
	{
		if (endIndex == startIndex)
		{
			// only one element
			Vector<Integer> newData = new Vector<>();
			newData.add(data.get(startIndex));
			return newData;
		}
		
		int middleIndex = startIndex + (endIndex - startIndex) / 2;
		return merge(sortInternal(data, startIndex, middleIndex), sortInternal(data, middleIndex + 1, endIndex));
	}
	
	protected Vector<Integer> merge(Vector<Integer> dataA, Vector<Integer> dataB)
	{
		Vector<Integer> retVal = new Vector<Integer>();
		
		int i = 0, j = 0;
		while(i < dataA.size() && j < dataB.size())
		{
			if (dataA.get(i) < dataB.get(j))
			{
				retVal.add(dataA.get(i++));
			}
			else if (dataA.get(i) > dataB.get(j))
			{
				retVal.add(dataB.get(j++));
			}
			else
			{
				retVal.add(dataA.get(i++));
				retVal.add(dataB.get(j++));
			}
		}
	
		while(i < dataA.size())
		{
			retVal.add(dataA.get(i++));
		}
		while(j < dataB.size())
		{
			retVal.add(dataB.get(j++));
		}
		
		return retVal;
	}
}