package service.customerAccount;

import databaseLayer.customer.IInfoInsertor;
import databaseLayer.customer.IInfoSelector;
import databaseLayer.customer.InfoInsertor;
import databaseLayer.customer.InfoSelector;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import service.customerData.*;
import view.customer.IInvalidValue;
import view.customer.InvalidValue;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SignupControllerTest {
    private IInfoInsertor insertor;
    private IInfoSelector selector;
    private ICredentialController credentialController;
    private IInfoController infoController;
    private IInvalidValue invalidValue;
    private ISignupHelper signupHelper;


    @Before
    public void init() {
        insertor = Mockito.mock(InfoInsertor.class);
        selector = Mockito.mock(InfoSelector.class);
        credentialController = Mockito.mock(CredentialController.class);
        infoController = Mockito.mock(InfoController.class);
        invalidValue = Mockito.mock(InvalidValue.class);
        signupHelper = new SignupHelper();
        signupHelper.setInsertor(insertor);
        signupHelper.setSelector(selector);
        signupHelper.setCredentialController(credentialController);
        signupHelper.setInfoController(infoController);
        signupHelper.setInvalidValue(invalidValue);
    }


    @Test
    public void checkCustomerChoiceTest1() {
        String choice = "y";
        SignupController signupController = new SignupController(signupHelper);
        when(invalidValue.getReentryChoice()).thenReturn(choice);
        boolean actual = signupController.checkCustomerChoice();
        Assertions.assertTrue(actual);
    }


    @Test
    public void checkCustomerChoiceTest2() {
        String choice = "n";
        SignupController signupController = new SignupController(signupHelper);
        when(invalidValue.getReentryChoice()).thenReturn(choice);
        boolean actual = signupController.checkCustomerChoice();
        Assertions.assertFalse(actual);
    }


    @Test
    public void setCredentialTest1() {
        String emailAddress = "abc@gmail.com";
        String password = "abc@123";
        List<String> credentials = new ArrayList<>();
        credentials.add(emailAddress);
        credentials.add(password);
        SignupController signupController = new SignupController(signupHelper);
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(credentialController.getEncryptedPassword()).thenReturn(password);
        when(insertor.setCredentials(credentials)).thenReturn(true);
        boolean actual = signupController.setCredentials();
        Assertions.assertTrue(actual);
    }


    @Test
    public void setCredentialTest2() {
        String choice = "n";
        String emailAddress = null;
        SignupController signupController = new SignupController(signupHelper);
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(invalidValue.getReentryChoice()).thenReturn(choice);
        boolean actual = signupController.setCredentials();
        Assertions.assertFalse(actual);
    }


    @Test
    public void setCredentialTest3() {
        String choice = "n";
        String emailAddress = "abc@gmail.com";
        String password = null;
        SignupController signupController = new SignupController(signupHelper);
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(credentialController.getEncryptedPassword()).thenReturn(password);
        when(invalidValue.getReentryChoice()).thenReturn(choice);
        boolean actual = signupController.setCredentials();
        Assertions.assertFalse(actual);
    }


    @Test
    public void attemptSignupAddCredentialsTest1() {
        String emailAddress = "abc@gmail.com";
        String password = "abc@123";
        List<String> credentials = new ArrayList<>();
        credentials.add(emailAddress);
        credentials.add(password);
        ISignupController signupController = new SignupController(signupHelper);
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(credentialController.getEncryptedPassword()).thenReturn(password);
        when(insertor.setCredentials(credentials)).thenReturn(true);
        when(selector.getCustomerID(emailAddress)).thenReturn(1);
        int expected = 1;
        int actual = signupController.attemptSignupAddCredentials();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void attemptSignupAddCredentialsTest2() {
        String choice = "n";
        String emailAddress = null;
        ISignupController signupController = new SignupController(signupHelper);
        when(credentialController.getEncryptedEmailAddress()).thenReturn(emailAddress);
        when(invalidValue.getReentryChoice()).thenReturn(choice);
        int expected = -1;
        int actual = signupController.attemptSignupAddCredentials();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void attemptSignupAddInfoTest1() {
        List<String> customerInfo = null;
        ISignupController signupController = new SignupController(signupHelper);
        when(infoController.getCustomerInfo()).thenReturn(customerInfo);
        boolean actual = signupController.attemptSignupAddInfo(1);
        Assertions.assertFalse(actual);
    }


    @Test
    public void attemptSignupAddInfoTest2() {
        int customerID = 1;
        String name = "Jacob";
        List<String> customerInfo = new ArrayList<>();
        customerInfo.add(name);
        ISignupController signupController = new SignupController(signupHelper);
        when(infoController.getCustomerInfo()).thenReturn(customerInfo);
        when(insertor.setCustomerInfo(customerID, customerInfo)).thenReturn(true);
        boolean actual = signupController.attemptSignupAddInfo(customerID);
        Assertions.assertTrue(actual);
    }
}