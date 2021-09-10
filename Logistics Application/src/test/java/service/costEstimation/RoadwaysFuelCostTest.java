package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class RoadwaysFuelCostTest
{
    @Mock
    private IFuelCost roadwaysFuelCostTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        roadwaysFuelCostTest = new RoadwaysFuelCost();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateCost() {
        final float calculatedRoadwaysFuelCost = roadwaysFuelCostTest.calculateCost(1, 1415.0F);
        Assertions.assertEquals(4245.0F,calculatedRoadwaysFuelCost);
    }
}
