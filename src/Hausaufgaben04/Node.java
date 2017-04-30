/**
 * Class Node represents an element which is used to store a string in a deque
 * 
 * @author C.Wassermann
 *
 */
public class Node {
	// pointer to next / previous elements
	public Node next;
	public Node prev;
	// content which is stored in the node
	public String content;

	/**
	 * Constructor which creates a new node with the given content
	 * 
	 * @param content
	 *            which is stored in the node
	 */
	public Node(String content) {
		this.content = content;
	}
}