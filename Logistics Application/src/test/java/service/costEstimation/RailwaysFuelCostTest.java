package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class RailwaysFuelCostTest
{
    @Mock
    private IFuelCost railwaysFuelCostTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        railwaysFuelCostTest = new RailwaysFuelCost();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateCost() {
        final float calculatedRailwaysFuelCost = railwaysFuelCostTest.calculateCost(3, 1409.0F);
        Assertions.assertEquals(4227.0F,calculatedRailwaysFuelCost);
    }
}
