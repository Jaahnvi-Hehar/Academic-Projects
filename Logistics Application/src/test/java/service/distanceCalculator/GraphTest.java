package service.distanceCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import java.util.List;

public class GraphTest {

    private List<Integer>[] adj;
    private IGraph graph;


    @Before
    public void init()
    {
        graph = new Graph(3, 3);
        adj = graph.getAdjacencyList();
    }

    @Test
    public void getAdjacencyListTest()
    {
        IGraph sample = Mockito.spy(graph);
        Assertions.assertNotNull(sample.getAdjacencyList());
    }

    @Test
    public void addEdgeTest()
    {
        int begin = adj.length;
        graph.addEdge(1, 2);
        adj = graph.getAdjacencyList();
        int end = adj.length;
        Assertions.assertEquals(begin, end);
    }
}
