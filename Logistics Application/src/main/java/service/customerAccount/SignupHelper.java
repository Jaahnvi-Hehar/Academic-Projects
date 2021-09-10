package service.customerAccount;

import databaseLayer.customer.IInfoInsertor;
import databaseLayer.customer.IInfoSelector;
import service.customerData.ICredentialController;
import service.customerData.IInfoController;
import view.customer.IInvalidValue;

public class SignupHelper implements ISignupHelper {
    private IInfoInsertor insertor;
    private IInfoSelector selector;
    private ICredentialController credentialController;
    private IInfoController infoController;
    private IInvalidValue invalidValue;


    @Override
    public IInfoInsertor getInsertor() {
        return insertor;
    }


    @Override
    public void setInsertor(IInfoInsertor insertor) {
        this.insertor = insertor;
    }


    @Override
    public IInfoSelector getSelector() {
        return selector;
    }


    @Override
    public void setSelector(IInfoSelector selector) {
        this.selector = selector;
    }


    @Override
    public ICredentialController getCredentialController() {
        return credentialController;
    }


    @Override
    public void setCredentialController(ICredentialController credentialController) {
        this.credentialController = credentialController;
    }


    @Override
    public IInfoController getInfoController() {
        return infoController;
    }


    @Override
    public void setInfoController(IInfoController infoController) {
        this.infoController = infoController;
    }


    @Override
    public IInvalidValue getInvalidValue() {
        return invalidValue;
    }


    @Override
    public void setInvalidValue(IInvalidValue invalidValue) {
        this.invalidValue = invalidValue;
    }
}