package service.customerData;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import view.customer.Info;
import view.customer.InvalidValue;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class InfoControllerTest {
    private Info info;
    private InvalidValue invalidValue;


    @Before
    public void init() {
        info = Mockito.mock(Info.class);
        invalidValue = Mockito.mock(InvalidValue.class);
        AbstractCustomerDataFactory.setUniqueInstance(new CustomerDateFactory());
    }

    @Test
    public void getInfoValidator() {
        InfoController controller = new InfoController(info, invalidValue);
        IInfoValidator infoValidator = controller.getInfoValidator();
        Assertions.assertNotNull(infoValidator);
    }

    @Test
    public void validateCustomerInfoTest1() {
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add("Jacob");
        customerInfo.add("01-01-2005");
        List<Boolean> validInfo = new ArrayList<>();
        validInfo.add(true);
        validInfo.add(true);
        InfoController controller = new InfoController(info, invalidValue);
        InfoController spyController = Mockito.spy(controller);
        InfoValidator infoValidator = Mockito.mock(InfoValidator.class);
        Mockito.doReturn(infoValidator).when(spyController).getInfoValidator();
        doNothing().when(invalidValue).printInvalid(anyString(), anyString());
        doReturn(validInfo).when(infoValidator).validateCustomerInfo(customerInfo);
        boolean actual = spyController.validateCustomerInfo(customerInfo);
        Assertions.assertTrue(actual);
    }


    @Test
    public void validateCustomerInfoTest2() {
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add("Jacob");
        customerInfo.add("01-01-2005");
        List<Boolean> validInfo = new ArrayList<>();
        validInfo.add(false);
        validInfo.add(true);
        InfoController controller = new InfoController(info, invalidValue);
        InfoController spyController = Mockito.spy(controller);
        InfoValidator infoValidator = Mockito.mock(InfoValidator.class);
        Mockito.doReturn(infoValidator).when(spyController).getInfoValidator();
        doNothing().when(invalidValue).printInvalid(anyString(), anyString());
        doReturn(validInfo).when(infoValidator).validateCustomerInfo(customerInfo);
        boolean actual = spyController.validateCustomerInfo(customerInfo);
        Assertions.assertFalse(actual);
    }

    @Test
    public void getCustomerInfoTest1() {
        String name = "Jacob", dob = "01-01-2005", houseNo = "Flat No. 201";
        String buildingName = "Sunrise Apartments", streetName = "21 Street";
        String area = "34 Area", city = "London", postalCode = "100002", contactNo = "9012345678";
        doReturn(name).when(info).getName();
        doReturn(dob).when(info).getDateOfBirth();
        doReturn(houseNo).when(info).getHouseNo();
        doReturn(buildingName).when(info).getBuildingName();
        doReturn(streetName).when(info).getStreetName();
        doReturn(area).when(info).getArea();
        doReturn(city).when(info).getPostalCode();
        doReturn(postalCode).when(info).getPostalCode();
        doReturn(contactNo).when(info).getContactNo();
        InfoController controller = new InfoController(info, invalidValue);
        InfoController spyController = Mockito.spy(controller);
        Mockito.doReturn(true).when(spyController).validateCustomerInfo(any());
        List<String> customerInfo = spyController.getCustomerInfo();
        Assertions.assertNotNull(customerInfo);
    }


    @Test
    public void getCustomerInfoTest2() {
        String name = "Jacob", dob = "01-01-2005", houseNo = "Flat No. 201";
        String buildingName = "Sunrise Apartments", streetName = "21 Street";
        String area = "34 Area", city = "London", postalCode = "100002", contactNo = "9012345678";
        doReturn(name).when(info).getName();
        doReturn(dob).when(info).getDateOfBirth();
        doReturn(houseNo).when(info).getHouseNo();
        doReturn(buildingName).when(info).getBuildingName();
        doReturn(streetName).when(info).getStreetName();
        doReturn(area).when(info).getArea();
        doReturn(city).when(info).getPostalCode();
        doReturn(postalCode).when(info).getPostalCode();
        doReturn(contactNo).when(info).getContactNo();
        InfoController controller = new InfoController(info, invalidValue);
        InfoController spyController = Mockito.spy(controller);
        Mockito.doReturn(false).when(spyController).validateCustomerInfo(any());
        List<String> customerInfo = spyController.getCustomerInfo();
        Assertions.assertNull(customerInfo);
    }
}
