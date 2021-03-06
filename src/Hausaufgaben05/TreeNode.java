/**
 * Class TreeNode resembles one node in a tree
 * 
 * Created by Tom on 06.05.2017.
 */
public class TreeNode {
	public int value;
	public TreeNode right, left, parent;

	public TreeNode(int value, TreeNode parent) {
		this.value = value;
		this.parent = parent;
		this.right = null;
		this.left = null;
	}

}
