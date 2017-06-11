
// short for DijkstraNode
public class DNode extends Node implements Comparable<DNode> {
	public int distance;
	public DNode route;
	public boolean visited;

	public DNode(int id) {
		super(id);
		this.distance = Integer.MAX_VALUE;
		this.route = null;
		this.visited = false;
	}

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
