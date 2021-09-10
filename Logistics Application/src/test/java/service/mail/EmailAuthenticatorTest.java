package service.mail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.mail.PasswordAuthentication;

public class EmailAuthenticatorTest {

    @Test
    public void getPasswordAuthenticationTest() {
        EmailAuthenticator emailAuthenticator = new EmailAuthenticator();
        PasswordAuthentication passwordAuthentication = emailAuthenticator.getPasswordAuthentication();
        Assertions.assertNotNull(passwordAuthentication);
    }
}
