package service.customerData.info;

public class HouseNo implements IIValidator {

    @Override
    public boolean isValid(String value) {
        return (0 < value.length());
    }
}