package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class AirwaysFuelCostTest
{
    @Mock
    private IFuelCost airwaysFuelCostTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        airwaysFuelCostTest = new AirwaysFuelCost();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateCost()
    {
        final float airFuelCost = airwaysFuelCostTest.calculateCost(2,1148.0f);
        Assertions.assertEquals(8610.0F,airFuelCost);

    }
}
