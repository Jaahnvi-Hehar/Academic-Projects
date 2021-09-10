package service.mail;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class EmailSenderTest {

    @Before
    public void init(){
        AbstractMailFactory.setUniqueInstance(new MailFactory());
    }

    @Test
    public void sendEmailTest1() {
        String emailAddress = "jaahnvi@gmail.com";
        String emailSubject = "Test Subject";
        String emailContent = "Test Email Content";
        EmailSender emailSender = Mockito.mock(EmailSender.class);
        doReturn(true).when(emailSender).sendEmail(emailAddress, emailSubject, emailContent);
        boolean actual = emailSender.sendEmail(emailAddress, emailSubject, emailContent);
        Assertions.assertTrue(actual);
    }

    @Test
    public void sendEmailTest2() {
        String emailAddress = "jaahnvi@gmail.com";
        String emailSubject = "Test Subject";
        String emailContent = "Test Email Content";
        EmailSender emailSender = Mockito.mock(EmailSender.class);
        doReturn(false).when(emailSender).sendEmail(emailAddress, emailSubject, emailContent);
        boolean actual = emailSender.sendEmail(emailAddress, emailSubject, emailContent);
        Assertions.assertFalse(actual);
    }
}
