package service.costEstimation;

public class RoadwaysMaintenanceCost implements IMaintenanceCost
{
    private static final Float ROAD_EMPLOYEE_COST = 100.0F;
    private static final Float ROAD_VEHICLE_COST = 170.0F;
    private static final Integer ROAD_WORKING_HOURS = 10;
    private static final Integer ROAD_WORKING_DAYS = 7;

    private float roadEmpSalaryHour;
    private float roadVehicleMaintenanceCost;

    public RoadwaysMaintenanceCost(){
        this.roadEmpSalaryHour = 0.0F;
        this.roadVehicleMaintenanceCost = 0.0F;
    }

    @Override
    public float calculateMaintenanceCost(int modeId) {
        roadEmpSalaryHour = ROAD_EMPLOYEE_COST / (ROAD_WORKING_HOURS * ROAD_WORKING_DAYS);
        roadVehicleMaintenanceCost = ROAD_VEHICLE_COST / (ROAD_WORKING_HOURS * ROAD_WORKING_DAYS);
        return roadEmpSalaryHour + roadVehicleMaintenanceCost;
    }
}
