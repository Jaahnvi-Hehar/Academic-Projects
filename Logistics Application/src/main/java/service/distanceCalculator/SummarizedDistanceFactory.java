package service.distanceCalculator;

public class SummarizedDistanceFactory extends AbstractSummarizedDistanceFactory{

    public IGraph createGraph(int vertices, int edges)
    {
        return new Graph(vertices, edges);
    }

    @Override
    public IDistance createDistance(IGraph graph) {
        return new Distance(graph);
    }


}
