package service.costEstimation;

public abstract class AbstractCostEstimationInformationFactory
{
    private static AbstractCostEstimationInformationFactory uniqueInstance = null;

    public static AbstractCostEstimationInformationFactory instance()
    {
        if(null == uniqueInstance)
        {
            uniqueInstance = new CostEstimationInformationFactory();
        }
        return uniqueInstance;
    }

    public static void setCostInformationFactory(AbstractCostEstimationInformationFactory abstractCostEstimationInformationFactory)
    {
        uniqueInstance = abstractCostEstimationInformationFactory;
    }

    public abstract ICostService createCostService();
    public abstract IFuelCost createFuelCostCalculator();
    public abstract IMaintenanceCost createMaintenanceCostCalculator();
    public abstract IFuelCost createAirwaysFuelCost();
    public abstract IFuelCost createRailwaysFuelCost();
    public abstract IFuelCost createRoadwaysFuelCost();
    public abstract IMaintenanceCost createAirwaysMaintenanceCost();
    public abstract IMaintenanceCost createRoadwaysMaintenanceCost();
    public abstract IMaintenanceCost createRailwaysMaintenanceCost();
}
