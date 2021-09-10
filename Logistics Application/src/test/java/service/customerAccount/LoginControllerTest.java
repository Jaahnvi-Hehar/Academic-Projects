package service.customerAccount;

import databaseLayer.customer.InfoSelector;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import service.encrypt.AbstractConvertor;
import service.encrypt.Decryptor;
import service.encrypt.Encryptor;
import view.customer.IInfo;
import static org.mockito.Mockito.when;

public class LoginControllerTest {
    private InfoSelector infoSelector;
    private IInfo info;
    private AbstractConvertor encryptor, decryptor;


    @Before
    public void init() {
        infoSelector = Mockito.mock(InfoSelector.class);
        info = Mockito.mock(IInfo.class);
        encryptor = new Encryptor();
        decryptor = new Decryptor();
    }


    @Test
    public void verifyLoginCredentialsTest1() {
        String emailAddress = "abc@gmail.com";
        String password = "abc@123";
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        String encryptedPassword = encryptor.alterText(password);
        LoginController loginController = new LoginController(infoSelector, info, encryptor, decryptor);
        when(infoSelector.getPassword(encryptedEmailAddress)).thenReturn(encryptedPassword);
        when(infoSelector.getCustomerID(encryptedEmailAddress)).thenReturn(1);
        int expected = 1;
        int actual = loginController.verifyLoginCredentials(emailAddress, password);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void verifyLoginCredentialsTest2() {
        String emailAddress = "abc@gmail.com";
        String password = null;
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        String encryptedPassword = null;
        LoginController loginController = new LoginController(infoSelector, info, encryptor, decryptor);
        when(infoSelector.getPassword(encryptedEmailAddress)).thenReturn(encryptedPassword);
        int expected = -1;
        int actual = loginController.verifyLoginCredentials(emailAddress, password);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void verifyLoginCredentialsTest3() {
        String emailAddress = "abc@gmail.com";
        String password = "abc@123";
        String wrongPassword = "abc@1222";
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        String encryptedPassword = encryptor.alterText(password);
        LoginController loginController = new LoginController(infoSelector, info, encryptor, decryptor);
        when(infoSelector.getPassword(encryptedEmailAddress)).thenReturn(encryptedPassword);
        int expected = -1;
        int actual = loginController.verifyLoginCredentials(emailAddress, wrongPassword);
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void attemptLoginTest1() {
        String emailAddress = "abc@gmail.com";
        String password = "abc@123";
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        String encryptedPassword = encryptor.alterText(password);
        when(info.getEmailAddress()).thenReturn(emailAddress);
        when(info.getPassword()).thenReturn(password);
        when(infoSelector.getPassword(encryptedEmailAddress)).thenReturn(encryptedPassword);
        when(infoSelector.getCustomerID(encryptedEmailAddress)).thenReturn(1);
        ILoginController loginController = new LoginController(infoSelector, info, encryptor, decryptor);
        int expected = 1;
        int actual = loginController.attemptLogin();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void attemptLoginTest2() {
        String emailAddress = "abc@gmail.com";
        String password = null;
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        when(info.getEmailAddress()).thenReturn(emailAddress);
        when(info.getPassword()).thenReturn(password);
        when(infoSelector.getPassword(encryptedEmailAddress)).thenReturn(password);
        ILoginController loginController = new LoginController(infoSelector, info, encryptor, decryptor);
        int expected = -1;
        int actual = loginController.attemptLogin();
        Assertions.assertEquals(expected, actual);
    }

}
