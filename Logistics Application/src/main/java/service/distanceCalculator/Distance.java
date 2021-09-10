package service.distanceCalculator;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Distance implements IDistance
{
	IGraph adjacencyList;
	List<Integer>[] adj;
	int length;
	boolean[] visitor;
	int[] parent;
	Queue<Integer> queue;

	public Distance(IGraph adjGraph)
	{
		this.adjacencyList = adjGraph;
		this.adj = adjacencyList.getAdjacencyList();
		this.length = adjacencyList.getAdjacencyList().length;
		this.visitor = new boolean[length];
		this.parent = new int[length];
		this.queue = new LinkedList<>();
	}

	public int calculateDistance(int source, int destination)
	{
		int distance = 0;
		int current;
		parent[source] = -1;
		visitor[source] = true;
		current = queueNeighborOperation(source, destination);
		distance = getDistance(distance, current);
		return distance;
	}

	private int queueNeighborOperation(int source, int destination)
	{
		int front;
		queue.add(source);
		Iterator<Integer> iter = queue.iterator();
		while (iter.hasNext())
		{
				front = queue.poll();
				if (front == destination)
				{
					break;
				}
				for (int neighbor : adj[front])
				{
					if (!visitor[neighbor])
					{
						visitor[neighbor] = true;
						queue.add(neighbor);
						parent[neighbor] = front;
					}
				}
			}
		return destination;
	}

	private int getDistance(int distance, int current)
	{
		while (parent[current] >= 0)
		{
			current = parent[current];
			distance++;
		}
		return distance;
	}

}
