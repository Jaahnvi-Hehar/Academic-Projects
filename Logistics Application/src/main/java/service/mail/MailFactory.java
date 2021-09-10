package service.mail;

import javax.mail.Authenticator;

public class MailFactory extends AbstractMailFactory {

    @Override
    public Authenticator createEmailAuthenticator() {
        return new EmailAuthenticator();
    }

    @Override
    public IEmailSender createEmailSender() {
        return new EmailSender();
    }
}
