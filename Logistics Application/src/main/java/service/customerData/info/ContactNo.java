package service.customerData.info;

public class ContactNo implements IIValidator {
    private static final String CONTACT_NO_REGEX = "^[0-9]{10}$";

    @Override
    public boolean isValid(String value) {
        return value.matches(CONTACT_NO_REGEX);
    }
}