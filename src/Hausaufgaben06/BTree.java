import java.util.ArrayList;

/**
 * Class BTree implements a BTree of order 2
 * Created by Manuel on 09.05.2017.
 */
public class BTree {
	// order and root
	private int order = 2;
	private BTreeNode root;
	// helper attributes
	private BTreeNode newNodeL;
	private BTreeNode newNodeR;

	/**
	 * Check if value x is contained in the BTree
	 * 
	 * @param x
	 *            value which is searched
	 * @return whether x is contained in the Btree or not
	 */
	public boolean search(int x) {
		if (this.root == null) { // empty tree
			return false;
		}
		if (searchNode(x, this.root) == null) {
			return true;
		}
		return false;
	}

	// recursive helper function for search
	private BTreeNode searchNode(int x, BTreeNode node) {
		int pos = 0;
		for (int value : node.values) {
			if (value == x) { // found occurrence
				return null;
			}
			if (value < x) { // not correct position
				pos++;
			}
			if (value > x) { // correct position found
				break;
			}
		}
		if (node.children == null) { // not found
			return node;
		}
		// recursive call with fitting child
		return searchNode(x, node.children.get(pos));
	}

	/**
	 * Insert value x into the Btree
	 * 
	 * @param x
	 *            value which is inserted
	 * @return the complexity of insertion (0: easy, 1: node size increased by
	 *         1, 2: number of layers increased by 1)
	 * @throws ArithmeticException
	 *             if value x is already contained in the BTree
	 */
	public int insert(int x) throws ArithmeticException {
		if (this.root == null) { // empty tree
			this.root = new BTreeNode();
			this.root.values = new ArrayList<>();
			this.root.values.add(x);
			return 0;
		} else {
			newNodeL = null;
			newNodeR = null;
			BTreeNode nodeToInsertTo = searchNode(x, this.root);
			if (nodeToInsertTo == null) {
				throw new ArithmeticException("Value already exists!");
			}
			return insertIntoNode(x, nodeToInsertTo);
		}
	}

	// recursive helper function for insert
	private int insertIntoNode(int x, BTreeNode node) {
		if (node == null) { // tree is extended
			node = new BTreeNode();
			node.values = new ArrayList<>();
			node.values.add(x);
			node.children = new ArrayList<>();
			node.children.add(newNodeL);
			newNodeL.parent = node;
			node.children.add(newNodeR);
			newNodeR.parent = node;
			root = node;
			return 2;
		}
		// determine insertion position
		int pos = 0;
		for (int value : node.values) {
			if (value == x) {
				throw new ArithmeticException("Value already exists!");
			}
			if (value < x) {
				pos++;
			}
			if (value > x) {
				break;
			}
		}
		node.values.add(pos, x);
		if (node.children != null) {
			node.children.remove(pos);
			node.children.add(pos, newNodeR);
			node.children.add(pos, newNodeL);
		}
		if (node.values.size() > 2 * order) {
			// split into left and right tree
			// --> middle value becomes new parent
			int mid = node.values.get(order);
			// new left tree
			newNodeL = new BTreeNode();
			newNodeL.values = new ArrayList<>();
			newNodeL.values.addAll(node.values.subList(0, order));
			newNodeL.parent = node.parent;
			if (node.children != null) {
				newNodeL.children = new ArrayList<>();
				newNodeL.children.addAll(node.children.subList(0, order));
			}
			// new right tree
			newNodeR = new BTreeNode();
			newNodeR.values = new ArrayList<>();
			newNodeR.values.addAll(node.values.subList(order + 1, node.values.size()));
			newNodeR.parent = node.parent;
			if (node.children != null) {
				newNodeR.children = new ArrayList<>();
				newNodeR.children.addAll(node.children.subList(order + 1, node.children.size()));
			}
			// insert middle value into upper layer
			int ret = insertIntoNode(mid, node.parent);
			// determine complexity of insertion
			if (ret == 2) {
				return 2;
			}
			return 1;
		} else {
			return 0;
		}
	}

	// test cases
	public static void main(String[] args) {
		BTree btree = new BTree();
		int[] toInsert = { 5, 15, 10, 20, 25, 30, 3, 4, 29, 28, 2, 21, 26, 22, 8, 12, 13 };
		String solution = "00002000011001002";
		String statusOfInsertion = "";
		for (int x : toInsert) {
			statusOfInsertion += btree.insert(x);
		}
		System.out.println("Das Einfuegen in den Baum hat richtig funktioniert: " + solution.equals(statusOfInsertion));
		System.out.println(btree.search(26)); // true
		System.out.println(btree.search(100)); // false
	}

}
