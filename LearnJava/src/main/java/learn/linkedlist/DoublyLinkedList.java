package learn.linkedlist;

import java.util.Vector;

public class DoublyLinkedList<T> {

	private DListNode<T> headNode;
	private DListNode<T> tailNode;

	public DoublyLinkedList()
	{
	}

	public DoublyLinkedList(DListNode<T> headNode)
	{
		this.headNode = headNode;
	}

	public DoublyLinkedList(Vector<T> dataList)
	{
		for (T data : dataList)
		{
			addNode(data);
		}
	}

	public DListNode<T> getHead() {
		return headNode;
	}

	public void setHead(DListNode<T> headNode) {
		this.headNode = headNode;
	}

	public void addNode(T data) {
		if (headNode == null)
		{
			headNode = new DListNode<T>(data, null, null);
			tailNode = headNode;
		}
		else
		{
			DListNode<T> newNode = new DListNode<T>(data, null, tailNode);
			tailNode = newNode;
		}
	}
	
	public void printList(String msg)
	{
		System.out.println("\n TRAVERSE_DOUBLY_LINKED_LIST: " + msg);
		System.out.print("<> data: ");
		
		DListNode<T> currentNode = headNode;
		while (currentNode != null)
		{
			System.out.print("   " + currentNode.getPrev() + "<-" + currentNode + "->" + currentNode.getNext() + "");
			currentNode = currentNode.getNext();
		}
		
		System.out.println();
	}

	public void reverseList() {
		DListNode<T> currNode = headNode;
		while (currNode != null)
		{
			DListNode<T> nextNode = currNode.getNext();
			currNode.reverse();
			headNode = currNode;
			currNode = nextNode;
		}
		
		printList("Reversed");
	}

	public void sort() {
		
	}
}
