package service.costEstimation;

public class RailwaysMaintenanceCost implements IMaintenanceCost
{
    private static final Float RAIL_EMPLOYEE_COST = 200.0F;
    private static final Float RAIL_VEHICLE_COST = 180.0F;
    private static final Integer RAIL_WORKING_HOURS = 12;
    private static final Integer RAIL_WORKING_DAYS = 7;

    private float railEmpSalaryHour;
    private float railVehicleMaintenanceCost;

    public RailwaysMaintenanceCost()
    {
        this.railEmpSalaryHour = 0.0F;
        this.railVehicleMaintenanceCost = 0.0F;
    }

    @Override
    public float calculateMaintenanceCost(int modeId) {
        railEmpSalaryHour = RAIL_EMPLOYEE_COST / (RAIL_WORKING_HOURS * RAIL_WORKING_DAYS);
        railVehicleMaintenanceCost = RAIL_VEHICLE_COST / (RAIL_WORKING_HOURS * RAIL_WORKING_DAYS);
        return railEmpSalaryHour + railVehicleMaintenanceCost;
    }
}
