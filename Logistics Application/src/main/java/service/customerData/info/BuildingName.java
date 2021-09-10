package service.customerData.info;

public class BuildingName implements IIValidator {
    private static final String BUILDING_NAME_REGEX = "[a-zA-Z0-9]+[a-zA-Z0-9 ]*";

    @Override
    public boolean isValid(String value) {
        return value.matches(BUILDING_NAME_REGEX);
    }
}