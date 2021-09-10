package service.costEstimation;

public class AirwaysFuelCost implements IFuelCost
{
    private static final Float AIR_FUEL_EFFICIENCY = 50.0F;
    private static final Float AIR_GAS_PER_FUEL_LITRE = 15.0F;
    private static final Integer PERCENTAGE_VALUE = 100;

    private float airNumberOfLitres;
    private float airFuelCost;

    public AirwaysFuelCost(){
        this.airNumberOfLitres = 0.0F;
        this.airFuelCost = 0.0F;
    }

    @Override
    public float calculateCost(int modeId,float distanceValue) {
        airNumberOfLitres = (AIR_FUEL_EFFICIENCY * distanceValue) / PERCENTAGE_VALUE;
        airFuelCost = AIR_GAS_PER_FUEL_LITRE * airNumberOfLitres;
        return airFuelCost;
    }
}
