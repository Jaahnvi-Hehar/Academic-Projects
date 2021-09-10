package service.customerAccount;

import databaseLayer.customer.IInfoInsertor;
import databaseLayer.customer.IInfoSelector;
import service.customerData.ICredentialController;
import service.customerData.IInfoController;
import view.customer.IInvalidValue;

public interface ISignupHelper {
    IInfoInsertor getInsertor();
    void setInsertor(IInfoInsertor insertor);
    IInfoSelector getSelector();
    void setSelector(IInfoSelector selector);
    ICredentialController getCredentialController();
    void setCredentialController(ICredentialController credentialController);
    IInfoController getInfoController();
    void setInfoController(IInfoController infoController);
    IInvalidValue getInvalidValue();
    void setInvalidValue(IInvalidValue invalidValue);
}
