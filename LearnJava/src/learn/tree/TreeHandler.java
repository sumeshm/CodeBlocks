package learn.tree;

import java.util.Scanner;
import java.util.Vector;

import learn.common.IHandler;
import learn.common.Menu;
import learn.tree.btree.BTreeNode;
import learn.tree.btree.TraversalOrder;

public class TreeHandler implements IHandler {
	
	private Vector<String> menuData;
	private BTreeNode bTreeHead;
	private Scanner scanner;
	private Menu menu;

	public TreeHandler()
	{
		createBTree();

		menuData = new Vector<String>();
		menuData.add("B-Tree: InOrder");
		menuData.add("B-Tree: PreOrder");
		menuData.add("B-Tree: PostOrder");
		menuData.add("B-Tree: LevelOrder");
		menuData.add("B-Tree: Search");
		menuData.add("B-Tree: Delete");
		menuData.add("B-Tree: Mirror");
		menuData.add("B-Tree: LeftView");

		menu = new Menu(menuData, "Tree Menu", this);
	}

	@Override
	public Menu getMenu()
	{
		return menu;
	}
	
	@Override
	public IHandler handle(int index)
	{
		switch (index)
		{
		case 0:
		{
			bTreeHead.printTree(TraversalOrder.TRAVERSE_IN_ORDER);
			break;
		}
		case 1:
		{
			bTreeHead.printTree(TraversalOrder.TRAVERSE_PRE_ORDER);
			break;
		}
		case 2:
		{
			bTreeHead.printTree(TraversalOrder.TRAVERSE_POST_ORDER);
			break;
		}
		case 3:
		{
			bTreeHead.printTree(TraversalOrder.TRAVERSE_LEVEL_ORDER);
			break;
		}
		case 4:
		{
			System.out.println(">>>>>>>>>>>> Enter the serach string: \n");
			scanner = new Scanner(System.in);
			String strInput = scanner.nextLine().trim();
			
			try {
				bTreeHead.search(new Integer(strInput));
			} catch (NumberFormatException e) {
				System.err.println("\n INVALID INPUT: try again \n");
			}
			
			break;
		}
		}
		
		return null;
	}
	
	protected void createBTree()
	{
		bTreeHead = new BTreeNode();

		System.out.println(">>>>>>>>>>>> Lets create the tree, enter an arrya of space seperated int(s):");
		System.out.println(">>>>>>>>>>>> e.g. 1 2 3 4  \n");

		scanner = new Scanner(System.in);
		String strInput = scanner.nextLine().trim();
		
		for (String value : strInput.split(" ")) {
			if (!value.trim().isEmpty())
			{
				bTreeHead.addNode(value);
			}
		}
	}


}
