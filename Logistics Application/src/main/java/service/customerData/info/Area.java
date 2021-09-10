package service.customerData.info;

public class Area implements IIValidator {
    private static final String AREA_REGEX = "[a-zA-Z0-9]+[a-zA-Z0-9 ]*";

    @Override
    public boolean isValid(String value) {
        return value.matches(AREA_REGEX);
    }
}