package service.mail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

//Referred source: https://www.tutorialspoint.com/javamail_api/javamail_api_send_html_in_email.htm
public class EmailAuthenticator extends Authenticator {
    private static final String FROM_EMAIL_ADDRESS = "akylaslogistics@gmail.com";
    private static final String FROM_EMAIL_PASSWORD = "Akylas@2021";

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(FROM_EMAIL_ADDRESS,FROM_EMAIL_PASSWORD);
    }
}
