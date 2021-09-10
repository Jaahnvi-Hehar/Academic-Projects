package service.customerAccount;

import databaseLayer.customer.IInfoInsertor;
import databaseLayer.customer.IInfoSelector;
import service.customerData.ICredentialController;
import service.customerData.IInfoController;
import view.customer.IInvalidValue;

import java.util.ArrayList;
import java.util.List;

public class SignupController implements ISignupController {
    private static final String YES = "y";
    private static final String NO = "n";
    private final IInfoInsertor insertor;
    private final IInfoSelector selector;
    private final ICredentialController credentialController;
    private final IInfoController infoController;
    private final IInvalidValue invalidValue;
    private String emailAddress, password;


    public SignupController(ISignupHelper signupHelper) {
        this.insertor = signupHelper.getInsertor();
        this.selector = signupHelper.getSelector();
        this.credentialController = signupHelper.getCredentialController();
        this.infoController = signupHelper.getInfoController();
        this.invalidValue = signupHelper.getInvalidValue();
    }


    public boolean checkCustomerChoice() {
        String choice = invalidValue.getReentryChoice();
        if (choice.equals(YES)) {
            return true;
        } else if (choice.equals(NO)) {
            return false;
        } else {
            return checkCustomerChoice();
        }
    }


    public void getEmailAddress() {
        emailAddress = credentialController.getEncryptedEmailAddress();
        if ((emailAddress == null) && (checkCustomerChoice())) {
            getEmailAddress();
        }
    }


    public void getPassword() {
        password = credentialController.getEncryptedPassword();
        if ((password == null) && (checkCustomerChoice())) {
            getPassword();
        }
    }


    public boolean setCredentials() {
        getEmailAddress();
        if (emailAddress == null) {
            return false;
        }
        getPassword();
        if (password == null) {
            return false;
        }
        List<String> credentials = new ArrayList<>();
        credentials.add(emailAddress);
        credentials.add(password);
        return insertor.setCredentials(credentials);
    }


    public int attemptSignupAddCredentials() {
        if (setCredentials()) {
            return selector.getCustomerID(emailAddress);
        }
        return -1;
    }


    public boolean attemptSignupAddInfo(int customerID) {
        List<String> customerInfo = infoController.getCustomerInfo();
        if (customerInfo == null) {
            return false;
        }
        return insertor.setCustomerInfo(customerID, customerInfo);
    }
}