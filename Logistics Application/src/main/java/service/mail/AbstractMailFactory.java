package service.mail;

import javax.mail.Authenticator;

public abstract class AbstractMailFactory {
    private static AbstractMailFactory uniqueInstance = null;

    public static AbstractMailFactory instance() {
        if(null == uniqueInstance)
        {
            uniqueInstance = new MailFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractMailFactory instance) {
        uniqueInstance = instance;
    }

    public abstract Authenticator createEmailAuthenticator();
    public abstract IEmailSender createEmailSender();
}
