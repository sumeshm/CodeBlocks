package learn.ds.linkedlist;

import java.util.Vector;

public class LinkedList<T> {
	
	private ListNode<T> headNode;

	public ListNode<T> getHeadNode() {
		return headNode;
	}

	public LinkedList(ListNode<T> headNode)
	{
		this.headNode = headNode;
	}

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
    
	public void sort() {
		headNode = mergeSort(getHeadNode());
		printList("Merged");
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
		
		System.out.println();
	}

	public void reverseList() {
		ListNode<T> prev = null;
		ListNode<T> curr = getHeadNode();
		ListNode<T> next = null;
		while (curr != null)
		{
			next = curr.getNext();
			curr.setNext(prev);
			
			prev = curr;
			curr = next;
		}
		
		headNode = prev;
		
		printList("Reversed");
	}

	private ListNode<T> mergeSort(ListNode<T> headNode) {
		
		if (headNode ==  null) {
			return null;
		}
		if (headNode.getNext() == null) {
			return headNode;
		}
		
		ListNode<T> midNode = getMidNode(headNode);
		if (midNode == null)
		{
			return headNode;
		}
		
		ListNode<T> tempNode = midNode.getNext();
		midNode.setNext(null);
		ListNode<T> leftNode = mergeSort(headNode);
		ListNode<T> rightNode = mergeSort(tempNode);

		return sortedAndMerge(leftNode, rightNode);
	}

	private ListNode<T> getMidNode(ListNode<T> headNode)
	{
		if (headNode == null)
		{
			return null;
		}

		ListNode<T> slow = headNode;
		ListNode<T> fast = headNode.getNext();
		while (fast != null && fast.getNext() != null)
		{
			slow = slow.getNext();
			fast = fast.getNext().getNext();
		}
		
		return slow;
	}

	private ListNode<T> sortedAndMerge(ListNode<T> leftNode, ListNode<T> rightNode) 
    {
		ListNode<T> retVal = null;

        if (leftNode == null) {
            return rightNode;
        }
        if (rightNode == null) {
        	return leftNode;
        }
        
        int leftData = (int) leftNode.getData();
        int rightData = (int) rightNode.getData();
        if (leftData <= rightData) 
        {
            retVal = leftNode;
            retVal.setNext(sortedAndMerge(leftNode.getNext(), rightNode));
        } 
        else
        {
        	retVal = rightNode;
        	retVal.setNext(sortedAndMerge(leftNode, rightNode.getNext()));
        }
 
        return retVal;
    }
}
