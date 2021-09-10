package service.costEstimation;

import java.util.HashMap;

public class MaintenanceCostCalculator implements IMaintenanceCost
{
    private static final Integer ROADWAYS_MODE_ID = 1;
    private static final Integer AIRWAYS_MODE_ID = 2;
    private static final Integer RAILWAYS_MODE_ID = 3;

    private final HashMap<Integer, IMaintenanceCost> transportMap;

    public MaintenanceCostCalculator()
    {
        AbstractCostEstimationInformationFactory abstractCostEstimationInformationFactory = AbstractCostEstimationInformationFactory.instance();
        this.transportMap = new HashMap<>();
        this.transportMap.put(ROADWAYS_MODE_ID, abstractCostEstimationInformationFactory.createRoadwaysMaintenanceCost());
        this.transportMap.put(AIRWAYS_MODE_ID, abstractCostEstimationInformationFactory.createAirwaysMaintenanceCost());
        this.transportMap.put(RAILWAYS_MODE_ID, abstractCostEstimationInformationFactory.createRailwaysMaintenanceCost());
    }

    @Override
    public float calculateMaintenanceCost(int modeId)
    {
        return this.transportMap.get(modeId).calculateMaintenanceCost(modeId);
    }
}
