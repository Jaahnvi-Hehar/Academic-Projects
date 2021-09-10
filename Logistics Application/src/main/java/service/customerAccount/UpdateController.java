package service.customerAccount;

import databaseLayer.customer.IInfoUpdator;
import service.customerData.ICredentialController;
import service.customerData.IInfoController;

import java.util.List;

public class UpdateController implements IUpdateController {
    private final ICredentialController credentialController;
    private final IInfoController infoController;
    private final IInfoUpdator updator;


    public UpdateController(IInfoUpdator updator, ICredentialController credentialController, IInfoController infoController) {
        this.updator = updator;
        this.credentialController = credentialController;
        this.infoController = infoController;
    }


    @Override
    public boolean updateEmailAddress(int customerID) {
        String emailAddress = credentialController.getEncryptedEmailAddress();
        if (emailAddress == null) {
            return false;
        }
        return updator.updateEmailAddress(customerID, emailAddress);
    }


    @Override
    public boolean updatePassword(int customerID) {
        String password = credentialController.getEncryptedPassword();
        if (password == null) {
            return false;
        }
        return updator.updatePassword(customerID, password);
    }


    @Override
    public boolean updateInfo(int customerID) {
        List<String> customerInfo = infoController.getCustomerInfo();
        if (customerInfo == null) {
            return false;
        }
        return updator.updateCustomerInfo(customerID, customerInfo);
    }
}
