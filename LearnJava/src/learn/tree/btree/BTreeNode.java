package learn.tree.btree;

import java.util.Stack;

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
		System.out.println("<> data: " + head.getData());

		if (head.getLeft() != null)
		{
			printInOrder(head.getLeft());
		}
		if (head.getRight() != null)
		{
			printInOrder(head.getRight());
		}
	}

	protected void printPreOrder(BTreeNode head)
	{
		if (head.getRight() != null)
		{
			printInOrder(head.getRight());
		}
		if (head.getLeft() != null)
		{
			printInOrder(head.getLeft());
		}

		System.out.println("<> data: " + head.getData());
	}

	protected void printPostOrder(BTreeNode head)
	{
		if (head.getLeft() != null)
		{
			printInOrder(head.getLeft());
		}
		if (head.getRight() != null)
		{
			printInOrder(head.getRight());
		}

		System.out.println("<> data: " + head.getData());
	}
	
	protected void printLevelOrder()
	{
		Stack<BTreeNode> stack = new Stack<BTreeNode>();
		System.out.println("\n TRAVERSE_LEVEL_ORDER: ");
		System.out.print("<> data: ");
		if (this.data != null)
		{
			stack.add(this);
		}
		
		while (!stack.isEmpty())
		{
			BTreeNode node = stack.pop();
			System.out.print(" " + node.getData());
			if (node.getLeft() != null)
			{
				stack.push(node.getLeft());
			}
			if (node.getRight() != null)
			{
				stack.push(node.getRight());
			}
		}
		
		System.out.println();
	}

	public BTreeNode searchInternal(BTreeNode current, Integer data) {
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
}
