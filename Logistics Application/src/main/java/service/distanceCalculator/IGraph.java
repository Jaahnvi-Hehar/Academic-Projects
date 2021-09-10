package service.distanceCalculator;

import java.util.List;

public interface IGraph {
	
	void addEdge(int startNode, int endNode);
	List<Integer>[] getAdjacencyList();

}
