package service.customerData;

import view.customer.IInfo;
import view.customer.IInvalidValue;

import java.util.ArrayList;
import java.util.List;

public class InfoController implements IInfoController {
    private static final String[] INFO_PARAMETERS = { "Name", "DOB", "HouseNo",
                                                    "BuildingName", "StreetName", "Area",
                                                    "City", "PostalCode", "ContactNo" };
    private final IInfo info;
    private final IInvalidValue invalidValue;


    public InfoController(IInfo info, IInvalidValue invalidValue) {
        this.info = info;
        this.invalidValue = invalidValue;
    }


    public IInfoValidator getInfoValidator() {
        return AbstractCustomerDataFactory.instance().createInfoValidator();
    }


    public boolean validateCustomerInfo(List<String> customerInfo) {
        IInfoValidator infoValidator = getInfoValidator();
        List<Boolean> validInfo = infoValidator.validateCustomerInfo(customerInfo);
        boolean isInfoValid = true;
        for (int i=0; i<validInfo.size(); i++) {
            String field = INFO_PARAMETERS[i];
            String value = customerInfo.get(i);
            boolean isValueValid = validInfo.get(i);
            if (isValueValid) {
                continue;
            }
            else {
                isInfoValid = false;
                invalidValue.printInvalid(field, value);
            }
        }
        return isInfoValid;
    }


    public List<String> getCustomerInfo() {
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add(info.getName());
        customerInfo.add(info.getDateOfBirth());
        customerInfo.add(info.getHouseNo());
        customerInfo.add(info.getBuildingName());
        customerInfo.add(info.getStreetName());
        customerInfo.add(info.getArea());
        customerInfo.add(info.getCity());
        customerInfo.add(info.getPostalCode());
        customerInfo.add(info.getContactNo());
        boolean valid = validateCustomerInfo(customerInfo);
        if (valid) {
            return customerInfo;
        }
        return null;
    }
}