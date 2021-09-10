package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import static org.mockito.MockitoAnnotations.openMocks;

public class MaintenanceCostCalculatorTest {

    @Mock
    private IMaintenanceCost maintenanceCostFactoryTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        AbstractCostEstimationInformationFactory.setCostInformationFactory(new CostEstimationInformationFactory());
        maintenanceCostFactoryTest = new MaintenanceCostCalculator();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCalculateMaintenanceCost() {
        final float calculatedMaintenanceCost = maintenanceCostFactoryTest.calculateMaintenanceCost(2);
        Assertions.assertEquals(12.5F,calculatedMaintenanceCost);
    }
}
