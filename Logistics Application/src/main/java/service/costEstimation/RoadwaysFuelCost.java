package service.costEstimation;

public class RoadwaysFuelCost implements IFuelCost
{
    private static final Float ROAD_FUEL_EFFICIENCY = 30.0F;
    private static final Float ROAD_GAS_PER_FUEL_LITRE = 10.0F;
    private static final Integer PERCENTAGE_VALUE = 100;

    private float roadNumberOfLitres;
    private float roadFuelCost;

    public RoadwaysFuelCost(){
        this.roadNumberOfLitres = 0.0F;
        this.roadFuelCost = 0.0F;
    }

    @Override
    public float calculateCost(int modeId, float distanceValue) {
        roadNumberOfLitres = (ROAD_FUEL_EFFICIENCY * distanceValue) / PERCENTAGE_VALUE;
        roadFuelCost = ROAD_GAS_PER_FUEL_LITRE * roadNumberOfLitres;
        return roadFuelCost;

    }
}
