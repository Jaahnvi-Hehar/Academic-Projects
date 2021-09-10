package service.costEstimation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.exceptions.base.MockitoException;
import java.util.HashMap;

public class CostServiceTest {

    @InjectMocks
    private CostService costServiceTest;

    @BeforeEach
    public void setUp() throws MockitoException {
        AbstractCostEstimationInformationFactory.setCostInformationFactory(new CostEstimationInformationFactory());
        costServiceTest = new CostService();
        final HashMap<String, Float> mockPackageDetails = new HashMap<>();
        mockPackageDetails.put("packageHeight", 3.0F);
        mockPackageDetails.put("packageWidth", 3.0F);
        mockPackageDetails.put("packageLength", 4.0F);
        mockPackageDetails.put("packageWeight", 30.0F);
        costServiceTest.setPackageDetails(mockPackageDetails, 1, 1415.0f);
    }

    @Test
    public void testEstimatedTotalCost() {
        final float estimatedTotalCost = costServiceTest.estimatedTotalCost();
        Assertions.assertEquals(46362.934F,estimatedTotalCost);
    }

    @Test
    public void testCalculateShippingCharge() {
        final float shippingCharge = costServiceTest.calculateShippingCharge();
        Assertions.assertEquals(3000.0F,shippingCharge);
    }

    @Test
    public void testCalculateFuelCost() {
        final float fuelCost = costServiceTest.calculateFuelCost();
        Assertions.assertEquals(42450.0F,fuelCost);
    }

    @Test
    public void testCalculateMaintenanceCost() {
        final float maintenanceCost = costServiceTest.calculateMaintenanceCost();
        Assertions.assertEquals(3.857143F,maintenanceCost);
    }

    @Test
    public void testProfitToCompany() {
        final float profitToCompany = costServiceTest.profitToCompany();
        Assertions.assertEquals(909.0771F,profitToCompany);
    }
}
