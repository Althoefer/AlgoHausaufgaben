
/**
 * Class DNode (short for DijkstraNode) represents a Node which can be used in
 * Dijkstra's shortest path algorithm
 * 
 * @author C.Wassermann
 *
 */
public class DNode extends Node implements Comparable<DNode> {
	// shortest found distance from the start node
	public int distance;
	// previous node on the shortest path from start
	public DNode route;
	// already finished in Dijkstra?
	public boolean visited;

	/**
	 * Constructor DNode creates a new DNode with ID id
	 * 
	 * @param id
	 *            the ID of the node
	 */
	public DNode(int id) {
		super(id);
		this.distance = Integer.MAX_VALUE;
		this.route = null;
		this.visited = false;
	}

	// implemented method from Comparable interface to make DNode usable in a
	// PriorityQueue
	@Override
	public int compareTo(DNode o) {
		if (this.distance < o.distance) {
			return -1;
		}
		if (this.distance == o.distance) {
			return 0;
		}
		return 1;
	}

}
