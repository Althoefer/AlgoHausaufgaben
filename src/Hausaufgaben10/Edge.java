/**
 * Class Edge represents a directed, weighted edge in a graph
 * 
 * @author C.Wassermann
 *
 */
public class Edge {

	// starting node
	public int from;
	// destination node
	public int to;
	// the weight of the edge
	public int weight;

	/**
	 * Constructor Edge creates a new edge with from starting node, to
	 * destination node and weight weight
	 * 
	 * @param from
	 *            starting node
	 * @param to
	 *            destination node
	 * @param weight
	 *            weight of the edge
	 */
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
}
