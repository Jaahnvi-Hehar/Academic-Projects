package service.costEstimation;

import java.util.HashMap;

public class FuelCostCalculator implements IFuelCost
{
    private static final Integer ROADWAYS_MODE_ID = 1;
    private static final Integer AIRWAYS_MODE_ID = 2;
    private static final Integer RAILWAYS_MODE_ID = 3;

    private final HashMap<Integer, IFuelCost> transportMap;

    public FuelCostCalculator()
    {
        AbstractCostEstimationInformationFactory abstractCostEstimationInformationFactory = AbstractCostEstimationInformationFactory.instance();
        this.transportMap = new HashMap<>();
        this.transportMap.put(ROADWAYS_MODE_ID, abstractCostEstimationInformationFactory.createRoadwaysFuelCost());
        this.transportMap.put(AIRWAYS_MODE_ID, abstractCostEstimationInformationFactory.createAirwaysFuelCost());
        this.transportMap.put(RAILWAYS_MODE_ID, abstractCostEstimationInformationFactory.createRailwaysFuelCost());
    }

    @Override
    public float calculateCost(int modeId,float distanceValue) {
        return this.transportMap.get(modeId).calculateCost(modeId,distanceValue);
    }
}
