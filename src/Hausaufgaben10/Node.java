import java.util.ArrayList;
import java.util.List;

public class Node {

	public int id;
	public List<Edge> edges;

	public Node(int id) {
		this.id = id;
		this.edges = new ArrayList<Edge>();
	}

}
