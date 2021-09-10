package service.customerData.info;

public class City implements IIValidator {
    private static final String CITY_REGEX = "[a-zA-Z]+[a-zA-Z ]*";

    @Override
    public boolean isValid(String value) {
        return value.matches(CITY_REGEX);
    }
}