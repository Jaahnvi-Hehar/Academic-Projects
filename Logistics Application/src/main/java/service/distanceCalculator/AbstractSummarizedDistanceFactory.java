package service.distanceCalculator;

public abstract class AbstractSummarizedDistanceFactory {

    public abstract IGraph createGraph(int vertices, int edges);

    public abstract IDistance createDistance(IGraph graph);
}
