package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class RoadwaysMaintenanceCostTest {

    @Mock
    private IMaintenanceCost roadwaysMaintenanceCostTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        roadwaysMaintenanceCostTest = new RoadwaysMaintenanceCost();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateMaintenanceCost() {
        final float calculatedRoadwaysMaintenanceCost = roadwaysMaintenanceCostTest.calculateMaintenanceCost(1);
        Assertions.assertEquals(3.857143F,calculatedRoadwaysMaintenanceCost);
    }
}
