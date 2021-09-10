package service.customerData;

public interface ICredentialController {
    String readEmailAddress();
    String readPassword();
    String getEncryptedEmailAddress();
    String getEncryptedPassword();
}
