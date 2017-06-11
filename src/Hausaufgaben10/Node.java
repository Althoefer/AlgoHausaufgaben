import java.util.ArrayList;
import java.util.List;

/**
 * Class Node represents a Node in a Graph
 * 
 * @author C.Wassermann
 *
 */
public class Node {

	// ID of the node
	public int id;
	// all edges starting from the node
	public List<Edge> edges;

	/**
	 * Constructor Node creates a new Node with ID id
	 * 
	 * @param id
	 *            the ID of the node
	 */
	public Node(int id) {
		this.id = id;
		this.edges = new ArrayList<Edge>();
	}

}
