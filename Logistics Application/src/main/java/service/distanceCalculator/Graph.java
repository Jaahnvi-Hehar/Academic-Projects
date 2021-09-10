package service.distanceCalculator;

import java.util.LinkedList;
import java.util.List;

public class Graph implements IGraph {

	private List<Integer>[] adj;
	int vertices;
	int edges;
	
	public Graph(int vertices, int edges) {

		initialize(vertices, edges);
	}

	private void initialize(int vertices, int edges)
	{
		this.vertices = vertices;
		this.edges = edges;

		adj = new LinkedList[vertices];

		for(int i = 0; i<vertices; i++)
		{
			adj[i] = new LinkedList<>();
		}
	}

	public List<Integer>[] getAdjacencyList()
	{
		return adj;
	}
	
	public void addEdge(int startNode, int endNode)
	{
		adj[startNode].add(endNode);
		adj[endNode].add(startNode);
	}

}
