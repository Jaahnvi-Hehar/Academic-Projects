package service.customerData.info;

public class PostalCode implements IIValidator {
    private static final String POSTAL_CODE_REGEX = "^[0-9]{6}$";

    @Override
    public boolean isValid(String value) {
        return value.matches(POSTAL_CODE_REGEX);
    }
}