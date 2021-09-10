package service.costEstimation;

public class AirwaysMaintenanceCost implements IMaintenanceCost
{
    private static final Float AIR_EMPLOYEE_COST = 400.0F;
    private static final Float AIR_VEHICLE_COST = 300.0F;
    private static final Integer AIR_WORKING_HOURS = 8;
    private static final Integer AIR_WORKING_DAYS = 7;

    private float airEmpSalaryHour;
    private float airVehicleMaintenanceCost;

    public AirwaysMaintenanceCost()
    {
        this.airEmpSalaryHour = 0.0F;
        this.airVehicleMaintenanceCost = 0.0F;
    }

    @Override
    public float calculateMaintenanceCost(int modeId) {
        airEmpSalaryHour = AIR_EMPLOYEE_COST / (AIR_WORKING_HOURS * AIR_WORKING_DAYS);
        airVehicleMaintenanceCost = AIR_VEHICLE_COST / (AIR_WORKING_HOURS * AIR_WORKING_DAYS);
        return airEmpSalaryHour + airVehicleMaintenanceCost;
    }
}
