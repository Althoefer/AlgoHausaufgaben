package src.Hausaufgaben10;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Class Dijkstra provides the functionality to find the shortest path in a
 * given graph by applying the Dijkstra algorithm to it
 * 
 * @author C.Wassermann
 *
 */
public class Dijkstra {

	/**
	 * Prints every iteration step of Dijkstra's shortest path algorithm
	 * 
	 * @param edges
	 *            a list which contains a description of the graph to solve in
	 *            the form: edges[0] = number of nodes in the Graph; one tuples
	 *            of three values per edge: from Node, to Node, edge weight
	 */
	public static void printDijkstra(int[] edges) {
		List<DNode> graph = new ArrayList<>();
		parseGraph(edges, graph);
		PriorityQueue<DNode> pq = new PriorityQueue<>();
		int start = 1;
		setStart(start, graph, pq);
		executeDijkstra(graph, pq);
	}

	// parses the graph input array into a list with fitting nodes
	// assumes that edges is a correctly formatted input array
	private static void parseGraph(int[] edges, List<DNode> graph) {
		int numNodes = edges[0];
		int numEdges = (edges.length - 1) / 3;
		graph.add(null); // dummy element
		// initialize every node in the graph
		for (int i = 1; i <= numNodes; ++i) {
			graph.add(new DNode(i));
		}
		// add edges to starting nodes
		for (int i = 0; i < numEdges; ++i) {
			int from = edges[1 + i * 3];
			int to = edges[1 + i * 3 + 1];
			int weight = edges[1 + i * 3 + 2];
			graph.get(from).edges.add(new Edge(from, to, weight));
		}
	}

	// alter the start node's attributes to setup for Dijkstra algorithm
	private static void setStart(int start, List<DNode> graph, PriorityQueue<DNode> pq) {
		DNode startNode = graph.get(start);
		startNode.distance = 0;
		startNode.route = startNode;
		pq.add(startNode);
	}

	// execute Dijkstra's shortest Path algorithm
	private static void executeDijkstra(List<DNode> graph, PriorityQueue<DNode> pq) {
		// retrieve the start node
		int start = pq.peek().id;
		outputHeader(start, graph);
		// main Dijkstra loop
		while (!pq.isEmpty()) {
			DNode currentNode = pq.poll();
			if (currentNode.visited) {
				continue;
			}
			currentNode.visited = true;
			for (Edge e : currentNode.edges) {
				DNode destination = graph.get(e.to);
				// check if new shortest path was found and adjust attributes
				// accordingly
				if (destination.distance > currentNode.distance + e.weight) {
					destination.distance = currentNode.distance + e.weight;
					destination.route = currentNode;
					pq.add(destination);
				}
			}
			outputStatus(start, currentNode.id, graph);
		}
	}

	// output iteration header
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

	// output the current status in Dijkstra algorithm
	private static void outputStatus(int start, int current, List<DNode> graph) {
		// vi
		System.out.printf("%2d|", current);
		// shortest found path
		for (int i = 1; i < graph.size(); ++i) {
			if (graph.get(i).id != start) {
				System.out.printf(" %2s",
						graph.get(i).distance == Integer.MAX_VALUE ? "--" : "" + graph.get(i).distance);
			}
		}
		System.out.print("|");
		// previous nodes on the shortest path from start
		for (int i = 1; i < graph.size(); ++i) {
			if (graph.get(i).id != start) {
				System.out.printf(" %2s", graph.get(i).route == null ? "--" : "" + graph.get(i).route.id);
			}
		}
		System.out.println("|");
	}
}
