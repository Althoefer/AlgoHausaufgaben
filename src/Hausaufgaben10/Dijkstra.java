import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {

	public static void printDijkstra(int[] edges) {
		List<DNode> graph = new ArrayList<>();
		parseGraph(edges, graph);
		PriorityQueue<DNode> pq = new PriorityQueue<>();
		int start = 1;
		setStart(start, graph, pq);
		executeDijkstra(graph, pq);
	}

	private static void parseGraph(int[] edges, List<DNode> graph) {
		int numNodes = edges[0];
		int numEdges = (edges.length - 1) / 3;
		graph.add(null);
		for (int i = 1; i <= numNodes; ++i) {
			graph.add(new DNode(i));
		}
		for (int i = 0; i < numEdges; ++i) {
			int from = edges[1 + i * 3];
			int to = edges[1 + i * 3 + 1];
			int weight = edges[1 + i * 3 + 2];
			graph.get(from).edges.add(new Edge(from, to, weight));
		}
	}

	private static void setStart(int start, List<DNode> graph, PriorityQueue<DNode> pq) {
		DNode startNode = graph.get(start);
		startNode.distance = 0;
		startNode.route = startNode;
		pq.add(startNode);
	}

	private static void executeDijkstra(List<DNode> graph, PriorityQueue<DNode> pq) {
		int start = pq.peek().id;
		outputHeader(start, graph);
		while (!pq.isEmpty()) {
			DNode currentNode = pq.poll();
			if (currentNode.visited) {
				continue;
			}
			currentNode.visited = true;
			for (Edge e : currentNode.edges) {
				DNode destination = graph.get(e.to);
				if (destination.distance > currentNode.distance + e.weight) {
					// new shortest Path
					destination.distance = currentNode.distance + e.weight;
					destination.route = currentNode;
					pq.add(destination);
				}
			}
			outputStatus(start, currentNode.id, graph);
		}
	}

	private static void outputHeader(int start, List<DNode> graph) {
		System.out.print("vi|");
		for (int rep = 0; rep < 2; ++rep) {
			for (int i = 1; i < graph.size(); ++i) {
				if (graph.get(i).id != start) {
					System.out.printf(" %2d", graph.get(i).id);
				}
			}
			System.out.print("|");
		}
		System.out.println();
		for (int i = 0; i < graph.size() * 2 * 3 - 2; ++i) {
			System.out.print("-");
		}
		System.out.println();
	}

	private static void outputStatus(int start, int current, List<DNode> graph) {
		System.out.printf("%2d|", current);
		for (int i = 1; i < graph.size(); ++i) {
			if (graph.get(i).id != start) {
				System.out.printf(" %2s",
						graph.get(i).distance == Integer.MAX_VALUE ? "--" : "" + graph.get(i).distance);
			}
		}
		System.out.print("|");
		for (int i = 1; i < graph.size(); ++i) {
			if (graph.get(i).id != start) {
				System.out.printf(" %2s", graph.get(i).route == null ? "--" : "" + graph.get(i).route.id);
			}
		}
		System.out.println("|");
	}
}
