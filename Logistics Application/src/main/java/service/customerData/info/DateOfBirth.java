package service.customerData.info;

public class DateOfBirth implements IIValidator {
    private static final String DOB_REGEX = "[0-9]{2}-[0-9]{2}-[0-9]{4}";

    @Override
    public boolean isValid(String value) {
        return value.matches(DOB_REGEX);
    }
}