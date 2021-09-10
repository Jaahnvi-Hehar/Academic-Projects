package service.costEstimation;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;

import static org.mockito.MockitoAnnotations.openMocks;

public class CostEstimationInformationTest {

    @InjectMocks
    private CostEstimationInformationFactory costEstimationInformationTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    public void setUp() throws MockitoException
    {
        mockitoCloseable = openMocks(this);
        costEstimationInformationTest = new CostEstimationInformationFactory();
    }

    @AfterEach
    public void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    public void testCreateCostService(){
        final ICostService iCostService = costEstimationInformationTest.createCostService();
        Assertions.assertNotNull(iCostService);
    }

    @Test
    public void testFuelCostCalculator()
    {
        final IFuelCost fuelCostCalculator = costEstimationInformationTest.createFuelCostCalculator();
        Assertions.assertNotNull(fuelCostCalculator);
    }

    @Test
    public void testMaintenanceCostCalculator()
    {
        final IMaintenanceCost maintenanceCostCalculator = costEstimationInformationTest.createMaintenanceCostCalculator();
        Assertions.assertNotNull(maintenanceCostCalculator);
    }

    @Test
    public void testAirwaysFuelCost()
    {
        final IFuelCost airwaysFuelCost = costEstimationInformationTest.createAirwaysFuelCost();
        Assertions.assertNotNull(airwaysFuelCost);
    }

    @Test
    public void testRailwaysFuelCost()
    {
        final IFuelCost railwaysFuelCost = costEstimationInformationTest.createRailwaysFuelCost();
        Assertions.assertNotNull(railwaysFuelCost);
    }

    @Test
    public void testRoadwaysFuelCost()
    {
        final IFuelCost roadwaysFuelCost = costEstimationInformationTest.createRoadwaysFuelCost();
        Assertions.assertNotNull(roadwaysFuelCost);
    }

    @Test
    public void testAirwaysMaintenanceCost()
    {
        final IMaintenanceCost airwaysMaintenanceCost = costEstimationInformationTest.createAirwaysMaintenanceCost();
        Assertions.assertNotNull(airwaysMaintenanceCost);
    }

    @Test
    public void testRoadwaysMaintenanceCost()
    {
        final IMaintenanceCost roadwaysMaintenanceCost = costEstimationInformationTest.createRoadwaysMaintenanceCost();
        Assertions.assertNotNull(roadwaysMaintenanceCost);
    }

    @Test
    public void testRailwaysMaintenanceCost()
    {
        final IMaintenanceCost railwaysMaintenanceCost = costEstimationInformationTest.createRailwaysMaintenanceCost();
        Assertions.assertNotNull(railwaysMaintenanceCost);
    }
}
