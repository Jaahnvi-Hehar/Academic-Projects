package service.customerData;

import service.customerData.info.IIValidator;

import java.util.ArrayList;
import java.util.List;

public class InfoValidator implements IInfoValidator {
    private final List<IIValidator> customerInfoValidator;


    public InfoValidator() {
        AbstractCustomerDataFactory customerFactory = AbstractCustomerDataFactory.instance();
        IIValidator name = customerFactory.createName();
        IIValidator dateOfBirth = customerFactory.createDateOfBirth();
        IIValidator houseNo = customerFactory.createHouseNo();
        IIValidator buildingName = customerFactory.createBuildingName();
        IIValidator streetName = customerFactory.createStreetName();
        IIValidator area = customerFactory.createArea();
        IIValidator city = customerFactory.createCity();
        IIValidator postalCode = customerFactory.createPostalCode();
        IIValidator contactNo = customerFactory.createContactNo();
        customerInfoValidator = new ArrayList<>();
        customerInfoValidator.add(name);
        customerInfoValidator.add(dateOfBirth);
        customerInfoValidator.add(houseNo);
        customerInfoValidator.add(buildingName);
        customerInfoValidator.add(streetName);
        customerInfoValidator.add(area);
        customerInfoValidator.add(city);
        customerInfoValidator.add(postalCode);
        customerInfoValidator.add(contactNo);
    }


    @Override
    public List<Boolean> validateCustomerInfo(List<String> values) {
        List<Boolean> validFields = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            IIValidator field = customerInfoValidator.get(i);
            String value = values.get(i);
            validFields.add(field.isValid(value));
        }
        return validFields;
    }
}