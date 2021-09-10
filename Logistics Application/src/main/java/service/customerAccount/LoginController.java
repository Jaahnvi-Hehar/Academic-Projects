package service.customerAccount;

import databaseLayer.customer.IInfoSelector;
import service.encrypt.AbstractConvertor;
import view.customer.IInfo;

public class LoginController implements ILoginController {
    private final IInfo info;
    private final IInfoSelector selector;
    private final AbstractConvertor encryptor;
    private final AbstractConvertor decryptor;


    public LoginController(IInfoSelector selector, IInfo info, AbstractConvertor encryptor, AbstractConvertor decryptor) {
        this.selector = selector;
        this.info = info;
        this.encryptor = encryptor;
        this.decryptor = decryptor;
    }


    public int verifyLoginCredentials(String emailAddress, String password) {
        String encryptedEmailAddress = encryptor.alterText(emailAddress);
        String confirmPassword = selector.getPassword(encryptedEmailAddress);
        if (confirmPassword == null) {
            return -1;
        }
        if (password.equals(decryptor.alterText(confirmPassword))) {
            return selector.getCustomerID(encryptedEmailAddress);
        }
        return -1;
    }


    public int attemptLogin() {
        String emailAddress = info.getEmailAddress();
        String password = info.getPassword();
        return verifyLoginCredentials(emailAddress, password);
    }
}
