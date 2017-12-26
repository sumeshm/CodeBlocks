package learn.tree.btree;

import java.util.Vector;

public class BTreeNode {

	private BTreeNode left;
	
	private BTreeNode right;
	
	private Integer data;

	public BTreeNode getLeft() {
		return left;
	}

	public void setLeft(BTreeNode left) {
		this.left = left;
	}

	public BTreeNode getRight() {
		return right;
	}

	public void setRight(BTreeNode right) {
		this.right = right;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		System.out.println(".....:" + data);
		this.data = data;
	}

	public boolean isEmpty()
	{
		return (data == null) ? true : false;
	}

	public boolean isLeaf()
	{
		if (left == null && right == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public void addNode(String data) {
		if (this.data == null)
		{
			setData(new Integer(data));
		}
		else
		{
			BTreeNode child = new BTreeNode();
			child.setData(new Integer(data));
			addNodeRecursive(this, child);
		}
	}

	public void printTree(TraversalOrder order)
	{
		switch (order)
		{
		case TRAVERSE_IN_ORDER:
		{
			System.out.println("\n TRAVERSE_IN_ORDER: ");
			printInOrder(this);
			break;
		}
		case TRAVERSE_PRE_ORDER:
		{
			System.out.println("\n TRAVERSE_PRE_ORDER: ");
			printPreOrder(this);
			break;
		}
		case TRAVERSE_POST_ORDER:
		{
			System.out.println("\n TRAVERSE_POST_ORDER: ");
			printPostOrder(this);
			break;
		}
		case TRAVERSE_LEVEL_ORDER:
		{
			printLevelOrder();
			break;
		}
		default:
			break;
		}
	}

	public BTreeNode search(Integer data) {
		System.out.println("\n SEARCH TREE: ");
		return searchInternal(this, data);
	}

	public void delete(Integer data) {
		System.out.println("\n DELETE TREE: ");
		deleteInternal(search(data));
	}


	public Vector<BTreeNode> getHeap()
	{
		int index = 0;
		Vector<BTreeNode> heap = new Vector<BTreeNode>();
		heap.add(this);

		while (heap.get(index) != null)
		{
			heap.add(heap.get(index).getLeft());
			heap.add(heap.get(index).getRight());
			index++;
		}
		
		return heap;
	}

	protected void addNodeRecursive(BTreeNode currentNode, BTreeNode newNode)
	{
		if (currentNode != null && currentNode.getData() != newNode.getData())
		{
			if (newNode.getData() < currentNode.getData())
			{
				if (currentNode.getLeft() == null)
				{
					currentNode.setLeft(newNode);
				}
				else
				{
					addNodeRecursive(currentNode.getLeft(), newNode);
				}
			}
			else if (newNode.getData() > currentNode.getData())
			{
				if (currentNode.getRight() == null)
				{
					currentNode.setRight(newNode);
				}
				else
				{
					addNodeRecursive(currentNode.getRight(), newNode);
				}
			}
		}
	}

	protected void printInOrder(BTreeNode head)
	{
		if (head.getLeft() != null)
		{
			printInOrder(head.getLeft());
		}
		
		System.out.println("<> data: " + head.getData());

		if (head.getRight() != null)
		{
			printInOrder(head.getRight());
		}
	}

	protected void printPreOrder(BTreeNode head)
	{
		System.out.println("<> data: " + head.getData());

		if (head.getLeft() != null)
		{
			printPreOrder(head.getLeft());
		}
		if (head.getRight() != null)
		{
			printPreOrder(head.getRight());
		}
	}

	protected void printPostOrder(BTreeNode head)
	{
		if (head.getLeft() != null)
		{
			printPostOrder(head.getLeft());
		}
		if (head.getRight() != null)
		{
			printPostOrder(head.getRight());
		}

		System.out.println("<> data: " + head.getData());
	}
	
	protected void printLevelOrder()
	{
		Vector<BTreeNode> heap = getHeap();
		System.out.println("\n TRAVERSE_LEVEL_ORDER: " + heap.size());
		System.out.print("<> data: ");

		for (BTreeNode node : heap)
		{
			if (node != null)
			{
				System.out.print(" " + node.getData());
			}
		}
		
		System.out.println();
	}

	protected BTreeNode searchInternal(BTreeNode current, Integer data) {
		if (current != null)
		{
			if (current.data.equals(data))
			{
				System.out.println("<> found: " + current.getData());
				return current;
			}
			else if (data < current.getData())
			{
				return searchInternal(current.getLeft(), data);
			}
			else if (data > current.getData())
			{
				return searchInternal(current.getRight(), data);
			}
		}		
		
		System.out.println("<> NOT found: " + data);
		return null;
	}
	
	protected void deleteInternal(BTreeNode current)
	{
	}

	protected void removeLeastNode(Vector<BTreeNode> heap, int index) {
		
		if (!heap.isEmpty())
		{
			BTreeNode node = heap.get(index);
			if (node.isLeaf())
			{
				return;
			}

			if (node.getLeft() != null)
			{
				heap.add(node.getLeft());
				removeLeastNode(heap, index++);
				return;
			}
			else if (node.getRight() != null)
			{
				heap.add(node.getRight());
				removeLeastNode(heap, index++);
				return;
			}
		}		
		
		System.out.println("<> NOT found: " + data);
	}
}
