package service.costEstimation;

import java.util.HashMap;

public class CostService implements ICostService
{
    private static final Integer COMPANY_PROFIT_PERCENT = 2;
    private static final Integer DIMENSION_DIVISOR = 130;
    private static final Integer COST_PER_WEIGHT = 100;
    private static final Integer DISTANCE_DEFAULT_MULTIPLIER = 10;

    private final IFuelCost iFuelCost;
    private final IMaintenanceCost iMaintenanceCost;

    private float packageWeight;
    private float packageHeight;
    private float packageWidth;
    private float packageLength;
    private int modeId;
    private float distanceValue;

    public CostService()
    {
        AbstractCostEstimationInformationFactory abstractCostEstimationInformationFactory = AbstractCostEstimationInformationFactory.instance();
        this.iFuelCost = abstractCostEstimationInformationFactory.createFuelCostCalculator();
        this.iMaintenanceCost = abstractCostEstimationInformationFactory.createMaintenanceCostCalculator();
    }

    @Override
    public void setPackageDetails(HashMap<String,Float> packageDetails,int modeId, float distanceValue){
        this.packageHeight = packageDetails.get("packageHeight");
        this.packageLength = packageDetails.get("packageLength");
        this.packageWeight = packageDetails.get("packageWeight");
        this.packageWidth = packageDetails.get("packageWidth");
        this.modeId = modeId;
        this.distanceValue = distanceValue;
    }

    @Override
    public float estimatedTotalCost()
    {
        float totalCost = 0.0F;
        totalCost += calculateShippingCharge() + calculateFuelCost() + calculateMaintenanceCost() + profitToCompany();
        return totalCost;
    }

    @Override
    public float calculateShippingCharge()
    {
        float actualWeight;
        float dimensionalWeight;
        float cubicSize;
        float shippingCost;

        actualWeight = this.packageWeight;
        cubicSize = this.packageHeight * this.packageWidth * this.packageLength;
        dimensionalWeight = cubicSize / DIMENSION_DIVISOR;

        if (dimensionalWeight > actualWeight){
            shippingCost = dimensionalWeight * COST_PER_WEIGHT;
        }
        else if (actualWeight > dimensionalWeight) {
            shippingCost = actualWeight * COST_PER_WEIGHT;
        }
        else{
            shippingCost = dimensionalWeight * COST_PER_WEIGHT;
        }
        return shippingCost;
    }

    @Override
    public float calculateFuelCost()
    {
        return iFuelCost.calculateCost(this.modeId,this.distanceValue * DISTANCE_DEFAULT_MULTIPLIER);
    }

    @Override
    public float calculateMaintenanceCost()
    {
        return iMaintenanceCost.calculateMaintenanceCost(this.modeId);
    }

    @Override
    public float profitToCompany()
    {
        float totalCost = (calculateMaintenanceCost()+calculateShippingCharge()+calculateFuelCost());
        return (COMPANY_PROFIT_PERCENT * totalCost) / 100;
    }
}
