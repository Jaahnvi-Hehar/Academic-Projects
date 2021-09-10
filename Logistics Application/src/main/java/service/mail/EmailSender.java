package service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//Referred source: https://www.tutorialspoint.com/javamail_api/javamail_api_send_html_in_email.htm
public class EmailSender implements IEmailSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
    private static final String HTML_MESSAGE_START = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>AKYLAS LOGISTICS PROVIDERS</title>\n" +
            "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\">\n" +
            "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">\n" +
            "</head>\n" +
            "<body style=\"font-family:'Roboto';\">\n" +
            "    <h3 style=\"color: #0d6efd;\">AKYLAS LOGISTICS PROVIDERS</h3>\n" +
            "    <p> ";
    private static final String HTML_MESSAGE_END =" </p>\n" +
            "</body>\n" +
            "</html>";
    private static final String[] EMAIL_PROPERTIES = {"mail.smtp.host", "mail.smtp.socketFactory.port", "mail.smtp.socketFactory.class", "mail.smtp.auth", "mail.smtp.port"};
    private static final String[] EMAIL_PROPERTIES_VALUES = {"smtp.gmail.com", "465", "javax.net.ssl.SSLSocketFactory", "true", "465"};
    private static final String EMAIL_CONTENT_TYPE = "text/html";
    private final Message message;


    public EmailSender() {
        Authenticator authenticator = AbstractMailFactory.instance().createEmailAuthenticator();
        Properties properties = new Properties();
        for (int i=0; i<EMAIL_PROPERTIES.length; i++) {
            properties.put(EMAIL_PROPERTIES[i], EMAIL_PROPERTIES_VALUES[i]);
        }
        Session session = Session.getDefaultInstance(properties, authenticator);
        message = new MimeMessage(session);
    }


    public boolean sendEmail(String toEmailAddress, String subject, String emailMessage) {
        try {
            String emailContent = HTML_MESSAGE_START + emailMessage + HTML_MESSAGE_END;
            InternetAddress internetAddress = new InternetAddress(toEmailAddress);
            message.addRecipient(Message.RecipientType.TO, internetAddress);
            message.setSubject(subject);
            message.setContent(emailContent, EMAIL_CONTENT_TYPE);
            Transport.send(message);
        }
        catch (MessagingException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
        return true;
    }
}
