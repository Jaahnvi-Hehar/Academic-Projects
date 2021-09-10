package service.costEstimation;

public class RailwaysFuelCost implements IFuelCost
{
    private static final Float RAIL_FUEL_EFFICIENCY = 20.0F;
    private static final Float RAIL_GAS_PER_FUEL_LITRE = 15.0F;
    private static final Integer PERCENTAGE_VALUE = 100;

    private float railNumberOfLitres;
    private float railFuelCost;

    public RailwaysFuelCost(){
        this.railNumberOfLitres = 0.0F;
        this.railFuelCost = 0.0F;
    }

    @Override
    public float calculateCost(int modeId, float distanceValue) {
        railNumberOfLitres = (RAIL_FUEL_EFFICIENCY * distanceValue) / PERCENTAGE_VALUE;
        railFuelCost = RAIL_GAS_PER_FUEL_LITRE * railNumberOfLitres;
        return railFuelCost;

    }
}
