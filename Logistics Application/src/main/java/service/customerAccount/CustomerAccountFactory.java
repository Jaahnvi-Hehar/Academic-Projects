package service.customerAccount;

import databaseLayer.customer.AbstractCustomerDatabaseFactory;
import databaseLayer.customer.IInfoSelector;
import databaseLayer.customer.IInfoUpdator;
import service.customerData.AbstractCustomerDataFactory;
import service.customerData.ICredentialController;
import service.customerData.ICredentialHelper;
import service.customerData.IInfoController;
import service.encrypt.AbstractConvertor;
import service.encrypt.AbstractConvertorFactory;
import view.customer.AbstractCustomerViewFactory;
import view.customer.IInfo;

public class CustomerAccountFactory extends AbstractCustomerAccountFactory {

    @Override
    public ISignupHelper createSignupHelper() {
        return new SignupHelper();
    }

    @Override
    public ISignupController createSignupController(ISignupHelper signupHelper) {
        return new SignupController(signupHelper);
    }

    @Override
    public ILoginController createLoginController() {
        IInfoSelector selector = AbstractCustomerDatabaseFactory.instance().createInfoSelector();
        IInfo info = AbstractCustomerViewFactory.instance().createInfo();
        AbstractConvertor encryptor = AbstractConvertorFactory.instance().createEncryptor();
        AbstractConvertor decryptor = AbstractConvertorFactory.instance().createDecryptor();
        return new LoginController(selector, info, encryptor, decryptor);
    }

    @Override
    public IUpdateController createUpdateController(ICredentialHelper credentialHelper) {
        IInfoUpdator updator = AbstractCustomerDatabaseFactory.instance().createInfoUpdator();
        IInfoController infoController = AbstractCustomerDataFactory.instance().createInfoController();
        ICredentialController credentialController = AbstractCustomerDataFactory.instance().createCredentialController(credentialHelper);
        return new UpdateController(updator, credentialController, infoController);
    }
}
