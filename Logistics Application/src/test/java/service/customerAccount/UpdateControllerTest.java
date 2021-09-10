package service.customerAccount;

import databaseLayer.customer.IInfoUpdator;
import databaseLayer.customer.InfoUpdator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import service.customerData.CredentialController;
import service.customerData.ICredentialController;
import service.customerData.IInfoController;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.when;

public class UpdateControllerTest {
    private ICredentialController credentialController;
    private IInfoController infoController;
    private IInfoUpdator updator;

    @Before
    public void init() {
        credentialController = Mockito.mock(CredentialController.class);
        infoController = Mockito.mock(IInfoController.class);
        updator = Mockito.mock(InfoUpdator.class);
    }

    @Test
    public void updateEmailAddressTest1() {
        int customerID = 1;
        String emailAddress = "abc@gmail.com";
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(updator.updateEmailAddress(customerID, emailAddress)).thenReturn(true);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updateEmailAddress(customerID);
        Assertions.assertTrue(actual);
    }

    @Test
    public void updateEmailAddressTest2() {
        int customerID = 1;
        String emailAddress = null;
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updateEmailAddress(customerID);
        Assertions.assertFalse(actual);
    }

    @Test
    public void updatePasswordTest1() {
        int customerID = 1;
        String password = "abc@123";
        when(credentialController.getEncryptedPassword()).thenReturn(password);
        when(updator.updatePassword(customerID, password)).thenReturn(true);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updatePassword(customerID);
        Assertions.assertTrue(actual);
    }

    @Test
    public void updatePasswordTest2() {
        int customerID = 1;
        String password = null;
        when(credentialController.getEncryptedPassword()).thenReturn(password);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updatePassword(customerID);
        Assertions.assertFalse(actual);
    }

    @Test
    public void updateInfoTest1(){
        int customerID = 1;
        String name = "Jacob";
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add(name);
        when(infoController.getCustomerInfo()).thenReturn(customerInfo);
        when(updator.updateCustomerInfo(customerID, customerInfo)).thenReturn(true);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updateInfo(customerID);
        Assertions.assertTrue(actual);
    }

    @Test
    public void updateInfoTest2(){
        int customerID = 1;
        List<String> customerInfo = null;
        when(infoController.getCustomerInfo()).thenReturn(customerInfo);
        IUpdateController updateController = new UpdateController(updator, credentialController, infoController);
        boolean actual = updateController.updateInfo(customerID);
        Assertions.assertFalse(actual);
    }
}
