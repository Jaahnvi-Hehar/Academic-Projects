package service.costEstimation;

public class CostEstimationInformationFactory extends AbstractCostEstimationInformationFactory
{
    @Override
    public ICostService createCostService()
    {
        return new CostService();
    }

    @Override
    public FuelCostCalculator createFuelCostCalculator()
    {
        return new FuelCostCalculator();
    }

    @Override
    public MaintenanceCostCalculator createMaintenanceCostCalculator()
    {
        return new MaintenanceCostCalculator();
    }

    @Override
    public IFuelCost createAirwaysFuelCost()
    {
        return new AirwaysFuelCost();
    }

    @Override
    public IFuelCost createRailwaysFuelCost()
    {
        return new RailwaysFuelCost();
    }

    @Override
    public IFuelCost createRoadwaysFuelCost()
    {
        return new RoadwaysFuelCost();
    }

    @Override
    public IMaintenanceCost createAirwaysMaintenanceCost()
    {
        return new AirwaysMaintenanceCost();
    }

    @Override
    public IMaintenanceCost createRoadwaysMaintenanceCost()
    {
        return new RoadwaysMaintenanceCost();
    }

    @Override
    public IMaintenanceCost createRailwaysMaintenanceCost()
    {
        return new RailwaysMaintenanceCost();
    }

}
