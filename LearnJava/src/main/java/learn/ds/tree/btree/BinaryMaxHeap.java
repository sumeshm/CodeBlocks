package learn.ds.tree.btree;

import java.util.Vector;

public class BinaryMaxHeap {
	Vector<Integer> heap = new Vector<>();

	public void add(int data)
	{
		heap.add(data);
	}

	public int extractMax()
	{
		int retVal = heap.firstElement();
		heap.set(0, heap.lastElement());
		heap.remove(heap.size() - 1);
		
		return retVal;
	}

	protected void heapify()
	{
		for (int index = heap.size() - 1; index > 0; index--)
		{
			while (index > 0)
			{
				int parentIndex = getParentIndex(index);
				if (heap.get(index) > heap.get(parentIndex))
				{
					int temp = heap.get(index);
					heap.set(index, heap.get(parentIndex));
					heap.set(parentIndex, temp);
				}

				index = parentIndex;
			}
		}
	}

	protected int getParentIndex(int index)
	{
		switch (index)
		{
		case 1:
			return 0;
		case 2:
			return 0;
		default:
		{
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
	}
}
