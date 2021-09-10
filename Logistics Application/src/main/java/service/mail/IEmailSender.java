package service.mail;

public interface IEmailSender {
    boolean sendEmail(String toEmailAddress, String subject, String emailMessage);
}
