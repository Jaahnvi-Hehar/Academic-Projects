package service.customerData;

import databaseLayer.admin.AdminPasswordConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import service.encrypt.AbstractConvertor;
import service.encrypt.Encryptor;
import service.mail.EmailSender;
import view.customer.Info;
import view.customer.InvalidValue;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doNothing;

public class CredentialControllerTest {
    private AdminPasswordConfig passwordConfig;
    private EmailSender emailSender;
    private AbstractConvertor encryptor;
    private Info info;
    private InvalidValue invalidValue;
    private CredentialHelper helper;


    @Before
    public void init() {
        passwordConfig = Mockito.mock(AdminPasswordConfig.class);
        emailSender = Mockito.mock(EmailSender.class);
        encryptor = Mockito.mock(Encryptor.class);
        info = Mockito.mock(Info.class);
        invalidValue = Mockito.mock(InvalidValue.class);
        helper = new CredentialHelper();
        helper.setPasswordConfig(passwordConfig);
        helper.setEmailSender(emailSender);
        helper.setEncryptor(encryptor);
        helper.setInfo(info);
        helper.setInvalidValue(invalidValue);
        AbstractCustomerDataFactory.setUniqueInstance(new CustomerDateFactory());
    }


    @Test
    public void validateEmailAddressTest1() {
        String emailAddress = "abc@gmail.com";
        CredentialController controller = new CredentialController(helper);
        boolean actual = controller.validateEmailAddress(emailAddress);
        Assertions.assertTrue(actual);
    }


    @Test
    public void validateEmailAddressTest2() {
        String emailAddress = "abc@..";
        CredentialController controller = new CredentialController(helper);
        boolean actual = controller.validateEmailAddress(emailAddress);
        Assertions.assertFalse(actual);
    }


    @Test
    public void checkVerificationCodeTest1() {
        String code1 = "abc123";
        String code2 = "abc123";
        when(info.getVerificationCode()).thenReturn(code1);
        CredentialController controller = new CredentialController(helper);
        boolean actual = controller.checkVerificationCode(code2);
        Assertions.assertTrue(actual);
    }


    @Test
    public void checkVerificationCodeTest2() {
        String code1 = "abc123";
        String code2 = "abc456";
        when(info.getVerificationCode()).thenReturn(code1);
        CredentialController controller = new CredentialController(helper);
        boolean actual = controller.checkVerificationCode(code2);
        Assertions.assertFalse(actual);
    }


    @Test
    public void generateCodeTest() {
        CredentialController controller = new CredentialController(helper);
        String actual = controller.generateCode();
        Assertions.assertNotNull(actual);
    }


    @Test
    public void verifyEmailAddressTest1() {
        String code = "abc123";
        String emailAddress = "abc@gmail.com";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(code).when(spyController).generateCode();
        when(emailSender.sendEmail(anyString(), anyString(), anyString())).thenReturn(true);
        Mockito.doReturn(true).when(spyController).checkVerificationCode(code);
        boolean actual = spyController.verifyEmailAddress(emailAddress);
        Assertions.assertTrue(actual);
    }


    @Test
    public void verifyEmailAddressTest2() {
        String code = "abc123";
        String emailAddress = "abc@gmail.com";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(code).when(spyController).generateCode();
        when(emailSender.sendEmail(anyString(), anyString(), anyString())).thenReturn(false);
        boolean actual = spyController.verifyEmailAddress(emailAddress);
        Assertions.assertFalse(actual);
    }


    @Test
    public void readEmailAddressTest() {
        String emailAddress = "abc@gmail.com";
        CredentialController credentialController = new CredentialController(helper);
        when(info.getEmailAddress()).thenReturn(emailAddress);
        String actual = credentialController.readEmailAddress();
        Assertions.assertNotNull(actual);
    }


    @Test
    public void readPasswordTest() {
        String password = "abc@123";
        CredentialController credentialController = new CredentialController(helper);
        when(info.getPassword()).thenReturn(password);
        String actual = credentialController.readPassword();
        Assertions.assertNotNull(actual);
    }


    @Test
    public void getPasswordValidatorTest() {
        CredentialController controller = new CredentialController(helper);
        IPasswordValidator actual = controller.getPasswordValidator();
        Assertions.assertNotNull(actual);
    }


    @Test
    public void validatePasswordTest1() {
        String password = "Abc@123";
        List<Boolean> validPassword = new ArrayList<>();
        validPassword.add(true);
        validPassword.add(true);
        validPassword.add(true);
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        PasswordValidator passwordValidator = Mockito.mock(PasswordValidator.class);
        Mockito.doReturn(passwordValidator).when(spyController).getPasswordValidator();
        doNothing().when(invalidValue).printInvalid(anyString(), anyString());
        doReturn(validPassword).when(passwordValidator).isPasswordValid(password, passwordConfig);
        boolean actual = spyController.validatePassword(password);
        Assertions.assertTrue(actual);
    }


    @Test
    public void validatePasswordTest2() {
        String password = "abc@1";
        List<Boolean> validPassword = new ArrayList<>();
        validPassword.add(true);
        validPassword.add(false);
        validPassword.add(true);
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        PasswordValidator passwordValidator = Mockito.mock(PasswordValidator.class);
        Mockito.doReturn(passwordValidator).when(spyController).getPasswordValidator();
        doNothing().when(invalidValue).printInvalid(anyString(), anyString());
        doReturn(validPassword).when(passwordValidator).isPasswordValid(password, passwordConfig);
        boolean actual = spyController.validatePassword(password);
        Assertions.assertFalse(actual);
    }


    @Test
    public void getEncryptedEmailAddressTest1() {
        String emailAddress = "abc@gmail.com";
        String encryptedEmailAddress = "g@moc.ilacabm";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(emailAddress).when(spyController).readEmailAddress();
        Mockito.doReturn(true).when(spyController).validateEmailAddress(emailAddress);
        Mockito.doReturn(true).when(spyController).verifyEmailAddress(emailAddress);
        doReturn(encryptedEmailAddress).when(encryptor).alterText(emailAddress);
        String expected = encryptedEmailAddress;
        String actual = spyController.getEncryptedEmailAddress();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void getEncryptedEmailAddressTest2() {
        String emailAddress = "abc@gmail.com";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(emailAddress).when(spyController).readEmailAddress();
        String expected = null;
        String actual = spyController.getEncryptedEmailAddress();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void getEncryptedPasswordTest1() {
        String password = "abc@123";
        String confirmPassword = "abc@123";
        String encryptedPassword = "1c@b23a";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(password).when(spyController).readPassword();
        doReturn(confirmPassword).when(info).getConfirmPassword();
        Mockito.doReturn(true).when(spyController).validatePassword(password);
        doReturn(encryptedPassword).when(encryptor).alterText(password);
        String expected = encryptedPassword;
        String actual = spyController.getEncryptedPassword();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void getEncryptedPasswordTest2() {
        String password = "abc@123";
        String confirmPassword = "abc@12345";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(password).when(spyController).readPassword();
        doReturn(confirmPassword).when(info).getConfirmPassword();
        String expected = null;
        String actual = spyController.getEncryptedPassword();
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void getEncryptedPasswordTest3() {
        String password = "abc@123";
        String confirmPassword = "abc@123";
        CredentialController controller = new CredentialController(helper);
        CredentialController spyController = Mockito.spy(controller);
        Mockito.doReturn(password).when(spyController).readPassword();
        doReturn(confirmPassword).when(info).getConfirmPassword();
        Mockito.doReturn(false).when(spyController).validatePassword(password);
        String expected = null;
        String actual = spyController.getEncryptedPassword();
        Assertions.assertEquals(expected, actual);
    }
}
