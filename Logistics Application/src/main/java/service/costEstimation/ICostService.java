package service.costEstimation;

import java.util.HashMap;

public interface ICostService
{
    public void setPackageDetails(HashMap<String,Float> packageDetails, int modeId, float distanceValue);
    public float estimatedTotalCost();
    public float calculateShippingCharge();
    public float calculateFuelCost();
    public float calculateMaintenanceCost();
    public float profitToCompany();
}
