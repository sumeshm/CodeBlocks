package learn.linkedlist;

import java.util.Vector;

public class LinkedList<T> {
	
	private ListNode<T> headNode;

	public LinkedList(Vector<T> dataList)
	{
		ListNode<T> prevNode = null;
		for (T data : dataList)
		{
			ListNode<T> currentNode = new ListNode<T>(data, null);
			if (headNode == null)
			{
				headNode = currentNode;
			}

			if (prevNode != null)
			{
				prevNode.setNext(currentNode);
				prevNode = currentNode;
			}
			else
			{
				prevNode = currentNode;
			}
		}
	}

	public void printList(String msg)
	{
		System.out.println("\n TRAVERSE_LINKED_LIST: " + msg);
		System.out.print("<> data: ");
		
		ListNode<T> currentNode = headNode;
		while (currentNode != null)
		{
			System.out.print(" " + currentNode.getData());
			currentNode = currentNode.getNext();
		}

	}
}
