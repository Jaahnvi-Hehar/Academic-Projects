package service.customerData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class InfoValidatorTest {

    @Test
    public void validateCustomerInfoTest1() {
        AbstractCustomerDataFactory.setUniqueInstance(new CustomerDateFactory());
        List<String> values = new ArrayList<>();
        String[] customerInfo = { "Jacob", "01-01-2005", "Flat No. 201", "Sunrise Apartments",
                "21 Street", "34 Area", "London", "100002", "9012345678"};
        for (int i=0; i<customerInfo.length; i++) {
            values.add(customerInfo[i]);
        }
        InfoValidator validator = new InfoValidator();
        List<Boolean> validFields = validator.validateCustomerInfo(values);
        for (int i=0; i<validFields.size(); i++) {
            Assertions.assertTrue(validFields.get(i));
        }
    }

    @Test
    public void validateCustomerInfoTest2() {
        AbstractCustomerDataFactory.setUniqueInstance(new CustomerDateFactory());
        List<String> values = new ArrayList<>();
        String[] customerInfo = { "123", "01/01-2005", "", "Sunrise@Apartments@",
                "21#Street#!", "34@Area", "London1", "1234567", "9012345678+23"};
        for (int i=0; i<customerInfo.length; i++) {
            values.add(customerInfo[i]);
        }
        InfoValidator validator = new InfoValidator();
        List<Boolean> validFields = validator.validateCustomerInfo(values);
        for (int i=0; i<validFields.size(); i++) {
            Assertions.assertFalse(validFields.get(i));
        }
    }
}
