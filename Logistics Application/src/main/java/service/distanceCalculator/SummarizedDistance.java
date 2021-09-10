package service.distanceCalculator;

public class SummarizedDistance implements ISummarizedDistance {

	AbstractSummarizedDistanceFactory distanceFactory;
	IGraph graph;
	private static final int VERTICES = 18;
	private static final int EDGES = 29;
	private static final int [][]EDGES_VALUE = {{0, 1}, {0, 12}, {0, 13}, {1, 2}, {1, 5}, {1, 6},
			{2, 3}, {2, 5}, {3, 4}, {3, 7}, {4, 5}, {4, 6}, {5, 6}, {5, 12}, {6, 7}, {7, 8}, {7, 10}, {8, 9},
			{9, 10}, {9, 11}, {10, 11}, {10, 12}, {11, 17}, {12, 17}, {13, 17}, {13, 14}, {14, 15}, {15, 16},
			{16, 17}};
	private static final int VERTEX1_INDEX = 0;
	private static final int VERTEX2_INDEX = 1;

	public SummarizedDistance()
	{
		distanceFactory = new SummarizedDistanceFactory();
		graph = distanceFactory.createGraph(VERTICES, EDGES);
		insertEdges();
	}
	
	public void insertEdges()
	{
		for (int[] ints : EDGES_VALUE) {
			graph.addEdge(ints[VERTEX1_INDEX], ints[VERTEX2_INDEX]);
		}
	}

	public int distance(int source, int destination)
	{
		IDistance distance = distanceFactory.createDistance(graph);
		return distance.calculateDistance(source, destination);
	}

}