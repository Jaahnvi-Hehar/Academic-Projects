package service.customerAccount;

import service.customerData.ICredentialHelper;

public abstract class AbstractCustomerAccountFactory {
    private static AbstractCustomerAccountFactory uniqueInstance = null;

    public static AbstractCustomerAccountFactory instance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CustomerAccountFactory();
        }
        return uniqueInstance;
    }

    public static void setUniqueInstance(AbstractCustomerAccountFactory instance) {
        uniqueInstance = instance;
    }

    public abstract ISignupHelper createSignupHelper();

    public abstract ISignupController createSignupController(ISignupHelper signupHelper);

    public abstract ILoginController createLoginController();

    public abstract IUpdateController createUpdateController(ICredentialHelper helper);
}
