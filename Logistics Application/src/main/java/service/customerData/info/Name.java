package service.customerData.info;

public class Name implements IIValidator {
    private static final String NAME_REGEX = "[a-zA-Z]+[a-zA-Z ]*";

    @Override
    public boolean isValid(String value) {
        return value.matches(NAME_REGEX);
    }
}