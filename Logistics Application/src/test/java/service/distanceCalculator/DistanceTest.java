package service.distanceCalculator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DistanceTest
{
    @Test
    public void distanceTrueTest()
    {
        IGraph graph = new Graph(3, 2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        IDistance distance = new Distance(graph);
        int dist = distance.calculateDistance(1, 1);
        Assertions.assertEquals(0, dist);
    }

    @Test
    public void distanceFalseTest()
    {
        IGraph graph = new Graph(3, 2);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        IDistance distance = new Distance(graph);
        int dist = distance.calculateDistance(1, 2);
        Assertions.assertNotEquals(0, dist);
    }
}
