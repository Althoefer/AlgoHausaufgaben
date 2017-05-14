import java.util.ArrayList;

/**
 * Class BTreeNode represents one node in a BTree
 * Created by Manuel on 09.05.2017.
 */
public class BTreeNode {
	// lists of value and children
	ArrayList<Integer> values;
	ArrayList<BTreeNode> children;
	// unique parent node
	BTreeNode parent;
}
