package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class RailwaysMaintenanceCostTest
{
    @Mock
    private IMaintenanceCost railwaysMaintenanceCostTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        railwaysMaintenanceCostTest = new RailwaysMaintenanceCost();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateMaintenanceCost()
    {
        final float calculatedRailwaysMaintenanceCost = railwaysMaintenanceCostTest.calculateMaintenanceCost(3);
        Assertions.assertEquals(4.5238094F,calculatedRailwaysMaintenanceCost);
    }
}
