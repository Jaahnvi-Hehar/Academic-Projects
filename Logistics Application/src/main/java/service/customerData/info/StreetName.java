package service.customerData.info;

public class StreetName implements IIValidator {
    private static final String STREET_NAME_REGEX = "[a-zA-Z0-9]+[a-zA-Z0-9 ]*";

    @Override
    public boolean isValid(String value) {
        return value.matches(STREET_NAME_REGEX);
    }
}